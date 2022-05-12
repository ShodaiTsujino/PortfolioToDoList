package katachi.spring.todoList.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import katachi.spring.todoList.controller.session.SessionData;
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
@SessionAttributes(types = SessionData.class)
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
	// セッション用クラス(検索内容)
	@Autowired
	private HttpSession session;
	// セッション用モデルクラス(作業リストのID)
	@Autowired
	private SessionData sessionData;

	// セッション初期化
	@ModelAttribute("sessionData")
	private SessionData getData() {
		return new SessionData();
	}

	/**
	 * 作業更新ページを表示
	 * 
	 * @param id     更新する対象の作業リストのID
	 * @param model
	 * @param locale
	 * @param form   フォームの内容を保持とバリデーション用
	 * @return 作業一覧ページから更新用ページへ
	 */
	@GetMapping("/update")
	public String getUpdate(int id, Model model, Locale locale, @ModelAttribute UpdateForm form) {
		// セッションデータにIDを格納
		sessionData.setId(id);
		// 作業一覧ページで選んだ項目のIDの作業内容を検索して呼び出し
		MUser user = userService.getTaskOne(id);
		// 呼び出した作業内容を表示させるために呼び出した項目をformに保存
		form = modelMapper.map(user, UpdateForm.class);
		// 全ての担当者を呼び出してViewに表示
		List<MUser> userList = userService.getUsers();
		// 呼び出したものをmodelに格納
		model.addAttribute("userList", userList);
		model.addAttribute("updateForm", form);
		// タイトルとヘッダー部分modelに格納
		model.addAttribute("title", messageSource.getMessage("update.title", null, locale));
		return "user/update";
	}

	/**
	 * 作業内容更新処理
	 * 
	 * @param form          バリデーション用
	 * @param bindingResult バリデーション結果
	 * @param model
	 * @param locale
	 * @return 内容更新後に直前の画面に遷移
	 */
	@PostMapping(value = "/update", params = "update")
	public String updateTaskOne(@ModelAttribute @Validated UpdateForm form, BindingResult bindingResult, Model model,
			Locale locale) {
		// セッションデータのIDを呼び出し
		int id = sessionData.getId();
		
		//作業が完了になったら完了日が登録済みの場合は登録済みの日付を格納、nullの場合は今日の日付を格納
		if (form.getCompleted() != 0) {// 完了にチェックが入った場合
			// 既に登録済みの完了日を呼び出す
			MUser user = userService.getTaskOne(id);
			// formに既に登録済みの完了日を格納
			form.setCompleteDate(user.getCompleteDate());
			if (form.getCompleteDate() == null) {// 完了日がnullだった場合
				Date today = new Date(); // 今日の日付生成
				form.setCompleteDate(today);// 完了日に今日の日付を入れる
			}
		}

		// formをMUserクラスに変換
		MUser user = modelMapper.map(form, MUser.class);
		user.setId(id);
		// バリデーションチェック
		if (bindingResult.hasErrors()) {
			// NG:ユーザー更新画面に戻ります
			return getEdit(model, locale, form);
		}
		// 作業内容更新処理
		userService.updateTaskOne(user);
		// 検索フォームに入力していた場合の遷移先分岐メソッド
		return back();
	}

	/**
	 * バリデーション用作業更新ページ 更新のバリデーション時に元々のフォームに内容が入っているためバリデーションがうまく働かないためのバリデーション用メソッド
	 * 
	 * @param model
	 * @param locale
	 * @param form   バリデーション用
	 * @return バリデーション処理後
	 */
	@GetMapping
	public String getEdit(Model model, Locale locale, @ModelAttribute UpdateForm form) {
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
	 * 更新ページから一覧ページの分岐
	 * 
	 * @return 検索結果がnullである場合に分岐して作業内容一覧と検索結果一覧に遷移先を決める
	 */
	@PostMapping(value = "/update", params = "cancel")
	public String back() {
		// セッションの検索内容の呼び出し
		String search = (String) session.getAttribute("search");
		// 検索フォームに入力していた場合の遷移先分岐
		if (search != null) {
			// 検索画面へ移動
			return "redirect:/user/search";
		}
		// 作業内容一覧へ移動
		return "redirect:/user/list";
	}
}
