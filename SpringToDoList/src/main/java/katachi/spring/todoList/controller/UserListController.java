package katachi.spring.todoList.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.spring.todoList.domain.user.model.MUser;
import katachi.spring.todoList.domain.user.service.UserService;

/**
 * 作業内容一覧画面を表示
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
	 *  作業一覧画面を表示
	 * @param model
	 * @param locale
	 * @return 作業一覧画面を表示
	 */
	@GetMapping("/list")
	public String getToDoList(Model model, Locale locale) {
		//セッションチェック
		if(session.getAttribute("search") != null) {
			//セッション削除
			session.removeAttribute("search");
		}
		//作業一覧をデータベースから呼び出し
		List<MUser> todoList = userService.getToDoList(null);
		// modelにViewの作業リストを表示
		model.addAttribute("todoList", todoList);
		// Viewのタイトルとヘッダー部分を表示
		model.addAttribute("title", messageSource.getMessage("list.title", null, locale));
		return "user/list";
	}

	/**
	 *  作業一覧画面から完了処理
	 * @return 完了処理をして作業内容一覧画面へ
	 */
	@PostMapping(value = "/list/{id}", params = "completed")
	public String postCompleted(
			@PathVariable(name = "id") int id,
			RedirectAttributes redirectAttributes,
			@SessionAttribute(name="search", required = false) String search
			) {
		redirectAttributes.addAttribute("search", search);
		//対象idのデータベースの完了日を更新
		userService.completeToDoOne(id);
		//作業一覧画面へリダイレクト
		return "redirect:/user/list";
	}

	/**
	 *  作業一覧画面から更新処理画面へ
	 * @return 更新処理画面へ
	 */
	@PostMapping(value = "/list/{id}", params = "update")
	public String postUpdate(@PathVariable(name = "id", required = false) int id) {
		//作業一覧画面へリダイレクト
		return "redirect:/user/update/{id}";
	}

	/**
	 *  作業一覧ページから削除処理画面へ
	 * @return 削除処理画面へ
	 */
	@PostMapping(value = "/list/{id}", params = "delete")
	public String postDelete(@PathVariable(name = "id", required = false) int id) {
		//作業一覧ページへリダイレクト
		return "redirect:/user/delete/{id}";
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
	public String search(
			@RequestParam(name = "search", required = false) String search,
			HttpServletRequest request,
			Model model,
			Locale locale
			) {
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
		List<MUser> todoList = userService.getToDoList(search);
		// modelにViewの作業リストを表示
		model.addAttribute("todoList", todoList);
		// Viewのタイトルとヘッダー部分を表示
		model.addAttribute("title", messageSource.getMessage("search.title", null, locale));
		return "user/list";
	}

	/**
	 *  作業一覧画面へ
	 * @return 検索結果から作業内容一覧画面へ
	 */
	@PostMapping(value = "/list")
	public String back() {
		session.removeAttribute("search");
		return "redirect:/user/list";
	}
}
