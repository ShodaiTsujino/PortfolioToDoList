package katachi.spring.todoList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * ログアウトクラス
 * @author S.Tsujino
 *
 */
@Controller
@Slf4j
public class LogoutController {

	/**
	 * ログイン画面にリダイレクト
	 * @return ログインページへ
	 */
	@PostMapping("/logout")
	public String postLogout() {
		log.info("ログアウト");
		return "redirect:/login";
	}
}
