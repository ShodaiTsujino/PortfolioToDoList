package katachi.spring.todoList.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import katachi.spring.todoList.domain.user.model.MUser;
import katachi.spring.todoList.domain.user.service.UserService;
/**
 * 作業内容一覧ページを表示
 * @author S.Tsujio
 *
 */
@Controller
@RequestMapping("/user")
public class UserListController {
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
	 *  ユーザー一覧画面を表示 
	 * @param model
	 * @param locale
	 * @param form 検索用のフォームを表示
	 * @return 作業一覧ペ＾ジを表示
	 */
	@GetMapping("/list")
	public String getUserList(Model model, Locale locale) {
		//作業一覧をデータベースから呼び出し
		List<MUser> taskList = userService.getTaskList();
		// modelにViewの作業リストを表示
		model.addAttribute("taskList",taskList);
		// Viewのタイトルとヘッダー部分を表示
		model.addAttribute("title", messageSource.getMessage("list.title", null, locale));
		return "user/list";
	}

	/**
	 *  作業一覧ページから完了処理
	 * @return 完了処理をして作業内容一覧ページへ
	 */
	@GetMapping(value = "/list", params = "completed")
	public String postCompleted(int id) {
		//データベースの指定したIDの作業内容を呼び出し
		MUser user = userService.getTaskOne(id);
		//完了日が入力済みの場合は何もしない
		if(user.getCompleteDate()==null) {
			//モデルの完了日に今日の日付を格納
			user.setCompleteDate(new Date());
			//データベースの指定したIDの作業内容を今日の日付にして完了済みに
			userService.updateTaskOne(user);
		}
		//作業一覧ページへリダイレクト
		return "redirect:/user/list";
	}

}
