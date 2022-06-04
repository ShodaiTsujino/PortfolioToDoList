package katachi.spring.todoList.controller;

import java.util.List;
import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.spring.todoList.domain.user.model.MUser;
import katachi.spring.todoList.domain.user.service.UserService;
import katachi.spring.todoList.form.UpdateForm;

/**
 * 作業内容更新ページ
 *
 * @author S.Tsujino
 *
 */
@Controller
@RequestMapping("/user")
@SessionAttributes(value = "sessionCompData")
public class TaskUpdateController {
	// データベースアクセス処理クラス
	@Autowired
	private UserService userService;
	// モデルクラス
	@Autowired
	private ModelMapper modelMapper;
	// Viewに表示する項目を呼び出すための項目
	@Autowired
	private MessageSource messageSource;



	/**
	 * 作業更新ページを表示
	 *
	 * @param id            更新する対象の作業項目ID
	 * @param search        入力した検索内容
	 * @param model
	 * @param locale
	 * @param form          フォームの内容を保持とバリデーション用
	 * @param sessionStatus
	 * @param sessionStatus セッション破棄
	 * @return 作業更新後に直前の画面に戻る
	 */
	@GetMapping("/update")
	public String getUpdate(int id, String search, Model model, Locale locale, @ModelAttribute UpdateForm form,
			SessionStatus sessionStatus) {
		// セッション削除
		sessionStatus.setComplete();
		// 作業一覧ページで選んだ項目のIDの作業内容を検索して呼び出し
		MUser user = userService.getTaskOne(id);
		// 呼び出した作業内容を表示させるために呼び出した項目をformに保存
		form = modelMapper.map(user, UpdateForm.class);
		// 全ての担当者を呼び出してViewに表示
		List<MUser> userList = userService.getUsers();
		// 呼び出したものをmodelに保存
		model.addAttribute("userList", userList);
		model.addAttribute("updateForm", form);
		model.addAttribute("search", search);
		model.addAttribute("id", id);
		// タイトルとヘッダー部分modelに登録
		model.addAttribute("title", messageSource.getMessage("update.title", null, locale));
		return "user/update";
	}

	/**
	 * 作業内容更新処理
	 *
	 * @param form               バリデーション用
	 * @param bindingResult      バリデーション結果
	 * @param id                 対象の作業内容のID
	 * @param model
	 * @param locale
	 * @param sessionData        セッション変数
	 * @param sessionStatus      セッション破棄
	 * @param search             入力した検索内容
	 * @param redirectAttributes
	 * @return 内容更新後に直前の画面に遷移
	 */
	@PostMapping(value = "/update", params = "update")
	public String updateTaskOne(@ModelAttribute @Validated UpdateForm form, BindingResult bindingResult, int id,
			Model model, Locale locale,SessionStatus sessionStatus, String search,
			RedirectAttributes redirectAttributes) {
		// 更新時に完了済みだった場合に
		if (form.getCompleted() != 0) {
			// 既に登録済みの完了日を呼び出す
			MUser user = userService.getTaskOne(id);
			form.setCompleteDate(user.getCompleteDate());
		}
		userService.completeDateFormat(form);

		// formをMUserクラスに変換
		MUser user = modelMapper.map(form, MUser.class);
		user.setId(id);
		// セッション削除
		sessionStatus.setComplete();
		//バリデーションチェック
		if (bindingResult.hasErrors()) {
			// NG:ユーザー更新画面に戻ります
			return getEdit(id, model, locale, form, search);
		}
		// 作業内容更新処理
		userService.updateTaskOne(user);
		// 検索フォームに入力していた場合の遷移先分岐
		if (search != "") {
			// リダイクレトに入力した検索内容を保存
			redirectAttributes.addAttribute("search", search);
			// 検索画面へ移動
			return "redirect:/user/search";
		}
		// 作業内容一覧へ移動
		return "redirect:/user/list";
	}

	/**
	 * バリデーション用作業更新ページ
	 *
	 * @param id     対象の項目のID
	 * @param model
	 * @param locale
	 * @param form   バリデーション用
	 * @param search 入力した検索内容
	 * @return バリデーション処理後
	 */
	@GetMapping
	public String getEdit(int id, Model model, Locale locale, @ModelAttribute UpdateForm form,
			@RequestParam String search) {
		// データベースのユーザー名を呼び出し
		List<MUser> userList = userService.getUsers();
		// 呼び出したものをパラメーターに格納
		model.addAttribute("userList", userList);
		model.addAttribute("updateForm", form);
		// Viewで表示するタイトルとヘッダー部分パラメータを格納
		model.addAttribute("title", messageSource.getMessage("update.title", null, locale));
		return "user/update";
	}

	/**
	 * 処理をキャンセルして一覧ページに戻る
	 *
	 * @param search             検索入力内容
	 * @param redirectAttributes 検索した内容をリダイレクト時に保持し、検索結果画面で表示するため
	 * @return 検索結果がnullである場合に分岐して作業内容一覧と検索結果一覧に遷移先を決める
	 */
	@PostMapping(value = "/update", params = "cancel")
	public String cancel(SessionStatus sessionStatus, String search, RedirectAttributes redirectAttributes) {
		// セッション破棄
		sessionStatus.setComplete();
		// 検索フォームに入力で場合の遷移先分岐
		if (search != "") {
			// リダイレクトに入力した検索内容を保存
			redirectAttributes.addAttribute("search", search);
			// 検索画面へ移動
			return "redirect:/user/search";
		}
		// 作業内容一覧へ移動
		return "redirect:/user/list";
	}

}
