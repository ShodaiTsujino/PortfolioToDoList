package katachi.spring.todoList.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import katachi.spring.todoList.domain.user.model.MUser;
import katachi.spring.todoList.domain.user.service.UserService;


/**
 * 作業内容一覧ページを表示
 * 
 * @author S.Tsujino
 *
 */
@Controller
@RequestMapping("/user")
public class UserListController {
	// データベースアクセスクラス
	@Autowired
	private UserService userService;
	// メッセージクラスを呼び出し
	@Autowired
	private MessageSource messageSource;
	// セッション用クラス(検索内容)
	@Autowired
	private HttpSession session;

	/**
	 * ユーザー一覧画面を表示
	 * 
	 * @param model
	 * @param locale
	 * @param request 検索フォームのセッション
	 * @param form    検索用のフォームを表示
	 * @return 作業一覧ページを表示
	 */
	@GetMapping("/list")
	public String getUserList(Model model, Locale locale, HttpServletRequest request) {
		// セッション削除・再生成
		session.removeAttribute("search");
		session = request.getSession();
		// 作業一覧をデータベースから呼び出し
		List<MUser> taskList = userService.getTaskList();
		// modelにViewの作業リストを表示
		model.addAttribute("taskList", taskList);
		// Viewのタイトルとヘッダー部分を表示
		model.addAttribute("title", messageSource.getMessage("list.title", null, locale));
		return "user/list";
	}

	/**
	 * 作業一覧ページから完了処理
	 * 
	 * @param id 選択した作業リストのID
	 * @return 完了処理をして作業内容一覧ページへ
	 */
	@GetMapping(value = "/list", params = "completed")
	public String getCompleted(int id) {
		// データベースの指定したIDの作業内容を呼び出し
		MUser user = userService.getTaskOne(id);
		// 完了日が入力済みの場合は何もしない
		if (user.getCompleteDate() == null) {
			// モデルの完了日に今日の日付を格納
			user.setCompleteDate(new Date());
			// データベースの指定したIDの作業内容を今日の日付にして完了済みに
			userService.updateTaskOne(user);
		}
		// 作業一覧ページへリダイレクト
		return "redirect:/user/list";
	}

	/**
	 * 検索結果が空の場合は作業一覧ページへ戻る
	 * 空じゃない場合は検索内容をセッションに保存して
	 * 作業一覧ページから検索結果ページへ
	 * 
	 * @param search  検索内容
	 * @param request セッションパラメータ
	 * @return 検索内容が空の場合は作業一覧ページへ、
	 * @return 検索内容をセッションに保存して検索一覧ページへ
	 */
	@PostMapping(value = "/list", params = "search")
	public String search(String search, HttpServletRequest request) {
		// 入力した検索フォームが空だった場合に検索一覧に戻る
		if (search.isEmpty()) {
			return "redirect:/user/list";
		}
		// セッション再生成
		session.removeAttribute("search");
		session = request.getSession();
		// セッションデータ設定
		session.setAttribute("search", search);
		return "redirect:/user/search";
	}
}