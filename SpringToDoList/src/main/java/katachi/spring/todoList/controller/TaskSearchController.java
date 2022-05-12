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
 * 検索内容画面コントローラー
 * 
 * @author S.Tsujino
 *
 */
@Controller
@RequestMapping("/user")
public class TaskSearchController {
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
	 * @param request 検索内容を保存したセッション
	 * @return 検索結果一覧画面を表示
	 */
	@GetMapping("/search")
	public String getUserSearchList(Model model, Locale locale, HttpServletRequest request) {
		// セッションの検索内容を呼び出し
		String search = (String) session.getAttribute("search");
		// URｌ直リンした場合に検索一覧に戻る
		if (search == null) {
			return "redirect:/user/list";
		} 
		// 検索内容の作業リストを呼び出してリストに保存
		List<MUser> taskList = userService.getSearchTaskList(search);
		// 呼び出した作業リストをパラメータに保存
		model.addAttribute("taskList", taskList);
		// タイトルとヘッダー部分をパラメータに保存
		model.addAttribute("title", messageSource.getMessage("search.title", null, locale));
		return "user/search";
	}

	/**
	 * 検索結果画面から完了処理
	 * 
	 * @param id 選択した作業リストのID
	 * @return 処理後に検索画面に戻る
	 */
	@PostMapping(value = "/search", params = "completed")
	public String postCompleted(int id) {
		// 指定した項目のIDでサービスクラスのデータ取得
		MUser user = userService.getTaskOne(id);
		// 完了日が「未」の場合に完了日に今日の日付を格納し、サービスクラスの完了処理へ
		if (user.getCompleteDate() == null) {
			// MUserの完了日に今日の日付を格納
			user.setCompleteDate(new Date());
			// サービスクラスの完了処理へ
			userService.updateTaskOne(user);
		}
		// 検索画面に移動
		return "redirect:/user/search";
	}

	// 検索画面から一覧画面へ
	@PostMapping(value = "/search", params = "back")
	public String back() {
	    //セッション削除
		session.removeAttribute("search");
		return "redirect:/user/list";
	}
}
