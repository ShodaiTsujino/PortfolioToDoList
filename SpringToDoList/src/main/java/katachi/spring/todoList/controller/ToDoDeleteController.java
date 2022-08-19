package katachi.spring.todoList.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.spring.todoList.domain.user.model.MUser;
import katachi.spring.todoList.domain.user.service.UserService;

/**
 * 削除処理画面コントローラー
 *
 * @author S.Tsujino
 *
 */
@Controller
@RequestMapping("/user")
public class ToDoDeleteController {
	//データベースアクセス処理クラス
	@Autowired
	private UserService userService;
	// Viewの各項目を表示するクラス
	@Autowired
	private MessageSource messageSource;

	/**
	 * 作業削除ページ
	 *
	 * @param id 指定した項目のID
	 * @return 削除処理画面へ
	 */
	@GetMapping("/delete/{id}")
	public String getDelete(
			Model model,Locale locale,
			@PathVariable("id") int id
			) {
		// 作業内容一覧画面で選んだ項目のIDを検索して呼び出してuserに格納
		MUser user = userService.getToDoOne(id);
		//VIewで表示するToDoリストを格納
		model.addAttribute("userList", user);
		// Viewで表示するタイトルとヘッダー部分パラメータを格納
		model.addAttribute("title", messageSource.getMessage("delete.title", null, locale));
		return "user/delete";
	}

	/**
	 * 作業内容削除処理
	 *
	 * @param id            	 対象の作業内容のID
	 * @param search             入力した検索内容
	 * @param redirectAttributes 検索内容をリダイレクトで送る
	 * @return 削除処理後に直前のページ遷移
	 */
	@PostMapping(value = "/delete/{id}", params = "delete")
	public String deleteToDoOne(
			@PathVariable("id") int id,
			@SessionAttribute(name="search", required = false) String search,
			RedirectAttributes redirectAttributes) {
		System.out.println(id);
		// 指定した項目のIDの作業内容更新処理へ
		userService.deleteToDoOne(id);
		//パラメータに検索結果を格納
		redirectAttributes.addAttribute("search", search);
		// 作業内容一覧へ移動
		return "redirect:/user/list";
	}
}
