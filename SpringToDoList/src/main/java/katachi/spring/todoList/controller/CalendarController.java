package katachi.spring.todoList.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import katachi.spring.todoList.domain.user.model.MUser;
import katachi.spring.todoList.domain.user.service.UserService;
import katachi.spring.todoList.form.AddForm;

@Controller
public class CalendarController {
	/**
	 * データベースアクセスクラス
	 */
	@Autowired
	private UserService userService;
	/**
	 * メッセージクラスを呼び出し
	 */
	@Autowired
	private MessageSource messageSource;
	/**
	 * Viewで表示するメッセージを常に取得
	 * @param model
	 * @param locale
	 */
	@ModelAttribute
	public void setVIewDisp(Model model,Locale locale) {
		// データベースのユーザー名を呼び出し
		List<MUser> userList = userService.getUsers();
		// VIewで表示するユーザー一覧をパラメーターに格納
		model.addAttribute("userList", userList);
		// Viewのタイトルとヘッダー部分を表示
		model.addAttribute("title", messageSource.getMessage("list.title", null, locale));
	}

    @GetMapping("user/schedule")
    String index(Model model,@ModelAttribute AddForm form) {

        return "calendar/selectable";
    }
}
