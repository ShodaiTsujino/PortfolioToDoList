package katachi.spring.todoList.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.spring.todoList.domain.user.model.MUser;
import katachi.spring.todoList.domain.user.service.UserService;
import katachi.spring.todoList.form.AddForm;

/**
 * 作業内容登録コントローラー
 *
 * @author S.Tsujino
 */
@Controller
@RequestMapping("/user")
public class ToDoAddController {
	/**
	 * データベースアクセス処理クラス
	 */
	@Autowired
	private UserService userService;
	/**
	 * モデルクラス
	 */
	@Autowired
	private ModelMapper modelMapper;
	/**
	 * Viewの各種項目を表示させるクラス
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * Viewで表示するメッセージを常に取得
	 * @param model
	 * @param locale
	 */
	@ModelAttribute
	public void setVIewDisp(Model model, Locale locale) {
		// データベースのユーザー名を呼び出し
		List<MUser> userList = userService.getUsers();
		// VIewで表示するユーザー一覧をパラメーターに格納
		model.addAttribute("userList", userList);
		// Viewで表示するタイトルとヘッダー部分パラメータを格納
		model.addAttribute("title", messageSource.getMessage("add.title", null, locale));
	}
	/**
	 * 作業登録ページを表示
	 * @return 作業登録ページへ移動
	 */
	@GetMapping("/add")
	public String getAddVIew(@ModelAttribute AddForm form) {
		return "user/add";
	}

	/**
	 * 作業内容登録処理
	 *
	 * @param form               入力した内容の保持とバリデーション
	 * @param search             検索結果の内容
	 * @param redirectAttributes 検索した内容をリダイレクト時に保持し、検索結果画面で表示するため
	 * @return 検索結果がnullである場合に分岐して作業内容一覧と検索結果一覧に遷移先を決める
	 */
	@PostMapping(value = "/add", params = "add")
	public String addToDoOne(
			@ModelAttribute @Validated AddForm form,
			BindingResult bindingResult,
			@SessionAttribute(name="search", required = false) String search,
			RedirectAttributes redirectAttributes
			) {
		// バリデーション
		if (bindingResult.hasErrors()) {
			// NG:ユーザー登録画面に戻ります
			return "user/add";
		}
		// 登録時の完了にチェックが入っている場合に今日の日付をフォームの完了日に格納
		if (form.getCompleted() != 0) {
			form.setCompleteDate(new Date());
		}
		// フォームクラスをMUserクラスに格納
		MUser user = modelMapper.map(form, MUser.class);
		// データベースに作業内容を登録
		userService.addToDoOne(user);
		//パラメータに検索結果を格納
		redirectAttributes.addAttribute("search", search);
		// 作業内容一覧へ移動
		return "redirect:/user/list";
	}
	/**
	 * キャンセルボタン押下で作業一覧画面へ戻る
	 * @param search
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping(value = "/add", params = "cancel")
	public String back(
			@SessionAttribute(name = "search", required = false) String search
			,RedirectAttributes redirectAttributes
			) {
		//パラメータに検索結果を格納
		redirectAttributes.addAttribute("search", search);
		// 作業内容一覧へ移動
		return "redirect:/user/list";
	}
}
