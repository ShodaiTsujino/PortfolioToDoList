package katachi.spring.todoList.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *ログインページのクラス 
 * @author S_Tsujino
 *
 */
@Controller
public class LoginController {
	@Autowired
	/*ページを構成する固定コードのメソッド*/
	private MessageSource messageSource;

	/**
	 * ログイン入力フォームのページを表示
	 * @param model
	 * @param locale
	 * @return
	 */
	@GetMapping("/login")
	public String getLogin(Model model, Locale locale) {
		model.addAttribute("title", messageSource.getMessage("login.title", null, locale));
		return "login/login";
	}

	/**
	 *ログイン処理を行い、作業内容一覧画面を表示
	 * @return ユーザー一覧画面にリダイレクト
	 */
		@PostMapping("/login")
		public String postLogin() {
			return "redirect:/user/list";
		}
}
