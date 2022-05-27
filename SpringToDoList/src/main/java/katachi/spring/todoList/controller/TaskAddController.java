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
public class TaskAddController {
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
	 * 作業登録ページを表示
	 * 
	 * @param model
	 * @param locale
	 * @param form   作業内容登録時の入力値を格納
	 * @param search 検索フォームに入力した内容を格納
	 * @return 作業登録ページへ移動
	 */
	@GetMapping("/add")
	public String getAdd(Model model, Locale locale, @ModelAttribute AddForm form, String search) {
		// ユーザーリスト検索
		List<MUser> userList = userService.getUsers();
		// Viewに各項目を表示させるパラメータを格納
		model.addAttribute("userList", userList);
		// 入力した検索内容のパラメータを格納
		model.addAttribute("search", search);
		// タイトルとヘッダー部分パラメータを格納
		model.addAttribute("title", messageSource.getMessage("add.title", null, locale));
		return "user/add";
	}

	/**
	 * 作業内容登録処理
	 *
	 * @param form               入力した内容の保持とバリデーション
	 * @param model
	 * @param locale
	 * @param search             検索結果の内容
	 * @param redirectAttributes 検索した内容をリダイレクト時に保持し、検索結果画面で表示するため
	 * @return 検索結果がnullである場合に分岐して作業内容一覧と検索結果一覧に遷移先を決める
	 */
	@PostMapping(value = "/add", params = "add")
	public String addTaskOne(@ModelAttribute @Validated AddForm form, BindingResult bindingResult, Model model,
			Locale locale, String search, RedirectAttributes redirectAttributes) {
		// バリデーション
		if (bindingResult.hasErrors()) {
			// NG:ユーザー登録画面に戻ります
			return getAdd(model, locale, form, search);
		}
		// 登録時の完了にチェックが入っている場合に今日の日付をフォームの完了日に格納
		if (form.getComplete() != 0) {
			form.setCompleteDate(new Date());
		}
		// フォームクラスをMUserクラスに格納
		MUser user = modelMapper.map(form, MUser.class);
		// データベースに作業内容を登録
		userService.addTaskOne(user);
		// 検索フォームに入力していた場合の遷移先分岐
		if (search != "") {
			// リダイレクトに入力した検索内容を保存
			redirectAttributes.addAttribute("search", search);
			// 検索内容を保持した状態で検索画面へ移動
			return "redirect:/user/search";
		}
		// 作業内容一覧へ移動
		return "redirect:/user/list";
	}

	/**
	 * 登録ページからキャンセルして直前の画面に戻る
	 * 
	 * @param search             入力した検索内容
	 * @param redirectAttributes 検索した内容をリダイレクト時に保持し、検索結果画面で表示するため
	 * @return 検索結果がnullである場合に分岐して作業内容一覧と検索結果一覧に遷移先を決める
	 */
	@PostMapping(value = "/add", params = "cancel")
	public String processCancel(String search, RedirectAttributes redirectAttributes) {
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
}
