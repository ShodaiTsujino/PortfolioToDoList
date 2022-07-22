package katachi.spring.todoList.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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
	 * セッション用クラス(検索内容)
	 */
	@Autowired
	private HttpSession session;

	@ModelAttribute
	public void setVIewDisp(Model model, Locale locale) {
		// データベースのユーザー名を呼び出し
		List<MUser> userList = userService.getUsers();
		// VIewで表示するユーザー一覧をパラメーターに格納
		model.addAttribute("userList", userList);
		// Viewで表示するタイトルとヘッダー部分パラメータを格納
		model.addAttribute("title", messageSource.getMessage("update.title", null, locale));
	}

	/**
	 * 作業更新ページを表示
	 *
	 * @param id     更新する対象の作業項目ID
	 * @param model
	 * @param locale
	 * @param form   フォームの内容を保持とバリデーション用
	 * @return 作業更新後に直前の画面に戻る
	 */
	@GetMapping("/update/{id}")
	public String getUpdate(@PathVariable("id") int id, Model model, @ModelAttribute UpdateForm form) {
		// 作業一覧ページで選んだ項目のIDの作業内容を検索して呼び出し
		MUser user = userService.getTaskOne(id);
		// 呼び出した作業内容を表示させるために呼び出した項目をformに保存
		form = modelMapper.map(user, UpdateForm.class);
		model.addAttribute("updateForm", form);
		return "user/update";
	}

	/**
	 * 作業内容更新処理
	 *
	 * @param form          バリデーション用
	 * @param bindingResult バリデーション結果
	 * @param id            対象の作業内容のID
	 * @param model
	 * @param locale
	 * @return 内容更新後に直前の画面に遷移
	 */
	@PostMapping(value = "/update/{id}", params = "update")
	public String updateTaskOne(
			@ModelAttribute @Validated UpdateForm form,
			BindingResult bindingResult,
			@PathVariable("id") int id,
			@SessionAttribute(name = "search", required = false) String search) {
		// バリデーションチェック
		if (bindingResult.hasErrors()) {
			// NG:ユーザー更新画面に戻ります
			return "user/update";
		}
		// 更新時に完了済みだった場合に
//		if (form.getCompleted() != 0) {
//			// 既に登録済みの完了日を呼び出す
//			MUser user = userService.getTaskOne(id);
//			// formに登録済みの完了日を格納
//			form.setCompleteDate(user.getCompleteDate());
//		}
//		// サービスクラスで完了日をフォーマット
//		userService.completeDateFormat(form);

		// formをMUserクラスに変換
		MUser user = modelMapper.map(form, MUser.class);
		user.setId(id);

		// 作業内容更新処理
		userService.updateTaskOne(user);
		System.out.println(search);
		if (!search.isEmpty()) {
			return "redirect:/user/list" + search;
		}
		// 作業内容一覧へ移動
		return "redirect:/user/list";
	}

}
