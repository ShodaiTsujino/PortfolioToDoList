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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

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
	 *  セッション用クラス(検索内容)
	 */
	@Autowired
	private HttpSession session;

	/**
	 *  ユーザー一覧画面を表示
	 * @param model
	 * @param locale
	 * @return 作業一覧ペ－ジを表示
	 */
	@GetMapping("/list")
	public String getUserList(String search, Model model, Locale locale) {
		session.removeAttribute("search");
		//作業一覧をデータベースから呼び出し
		List<MUser> taskList = userService.getTaskList(search);
		// modelにViewの作業リストを表示
		model.addAttribute("taskList", taskList);
		// Viewのタイトルとヘッダー部分を表示
		model.addAttribute("title", messageSource.getMessage("list.title", null, locale));
		return "user/list";
	}

	/**
	 *  作業一覧ページから完了処理
	 * @return 完了処理をして作業内容一覧ページへ
	 */
	@PostMapping(value = "/list", params = "completed")
	public String completed(@RequestParam(name = "id", required = false) int id) {
		//対象idのデータベースの完了日を更新
		userService.completeTaskOne(id);
		//作業一覧ページへリダイレクト
		return "redirect:/user/list";
	}

	/**
	 * 検索結果が空の場合は作業一覧ページへ戻る
	 * 空じゃない場合は検索内容をセッションに保存して
	 * 作業一覧ページから検索結果ページへ
	 *
	 * @param search  検索内容
	 * @param request セッションパラメータ
	 * @param model
	 * @param locale
	 * @return 検索内容が空の場合は作業一覧ページへ、
	 * @return 検索内容をセッションに保存して検索一覧ページへ
	 */
	@GetMapping(value = "/list", params = "search")
	public String search(@SessionAttribute("search") @RequestParam(name = "search", required = false) String search,
			HttpServletRequest request, Model model, Locale locale) {
		// 入力した検索フォームが空だった場合に検索一覧に戻る
		if (search.isEmpty()) {
			return "redirect:/user/list";
		}
		// セッション再生成
		session.removeAttribute("search");
		session = request.getSession();
		// セッションデータ設定
		session.setAttribute("search", search);
		//検索した内容でtodoリストをデータベースから呼び出し
		List<MUser> taskList = userService.getTaskList(search);
		// modelにViewの作業リストを表示
		model.addAttribute("taskList", taskList);
		// Viewのタイトルとヘッダー部分を表示
		model.addAttribute("title", messageSource.getMessage("search.title", null, locale));
		return "user/list";
	}

	/**
	 *  作業一覧ページへ
	 * @return 検索結果から作業内容一覧ページへ
	 */
	@PostMapping(value = "/list")
	public String back() {
		session.removeAttribute("search");
		return "redirect:/user/list";
	}
}
