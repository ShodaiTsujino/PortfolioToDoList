//package katachi.spring.todoList.controller;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * ログアウトクラス
// * @author S.Tsujino
// *
// */
//@Controller
//@Slf4j
//public class LogoutController {
//	/**
//	 *  セッション用クラス(検索内容)
//	 */
//	@Autowired
//	private HttpSession session;
//	/**
//	 * ログイン画面にリダイレクト
//	 * @return ログインページへ
//	 */
//	@PostMapping("/logout")
//	public String postLogout() {
//		System.out.println(session.getAttribute("search")+"bbb");
//		session.removeAttribute("search");
//		System.out.println(session.getAttribute("search")+"aaa");
//		log.info("ログアウト");
//		return "redirect:/login";
//	}
//}
