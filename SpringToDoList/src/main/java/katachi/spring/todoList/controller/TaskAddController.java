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
public class TaskAddController {
	// データベースアクセス処理クラス
	@Autowired
	private UserService userService;
	// モデルマッパークラス
	@Autowired
	private ModelMapper modelMapper;
	// Viewの各種項目を表示させるクラス
	@Autowired
	private MessageSource messageSource;
	// セッション用クラス
	@Autowired
	private HttpSession session;

	/**
	 * 作業登録ページを表示
	 * 
	 * @param model
	 * @param locale
	 * @param form   作業内容登録時の入力値を格納
	 * @return 作業登録ページへ移動
	 */
	@GetMapping("/add")
	public String getAdd(Model model, Locale locale, @ModelAttribute AddForm form) {
		// ユーザーリスト検索
		List<MUser> userList = userService.getUsers();
		// Viewに各項目を表示させるパラメータを格納
		model.addAttribute("userList", userList);
		// タイトルとヘッダー部分パラメータを格納
		model.addAttribute("title", messageSource.getMessage("add.title", null, locale));
		return "user/add";
	}

	/**
	 * 作業内容登録処理
	 *
	 * @param form   入力した内容の保持とバリデーション
	 * @param model
	 * @param locale
	 * @return 登録処理後に作業一覧ページか検索結果ページへ遷移
	 */
	@PostMapping(value = "/add", params = "add")
	public String addTaskOne(@ModelAttribute @Validated AddForm form, BindingResult bindingResult, Model model,
			Locale locale) {
		// バリデーション
		if (bindingResult.hasErrors()) {
			// NG:ユーザー登録画面に戻ります
			return getAdd(model, locale, form);
		}
		// 登録時の完了にチェックが入っている場合に今日の日付をフォームの完了日に格納
		if (form.getComplete() != 0) {
			form.setCompleteDate(new Date());
		}
		// フォームクラスをMUserクラスに格納
		MUser user = modelMapper.map(form, MUser.class);
		// データベースに作業内容を登録
		userService.addTaskOne(user);
		// 検索内容によって遷移先を決めるメソッド
		return back();
	}

	/**
	 * 更新ページから一覧ページへ
	 * 
	 * @return 検索結果がnullである場合に分岐して作業内容一覧と検索結果一覧に遷移先を決める
	 */
	@PostMapping(value = "/add", params = "cancel")
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
