package katachi.spring.todoList.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import katachi.spring.todoList.controller.session.SessionData;
import katachi.spring.todoList.domain.user.model.MUser;
import katachi.spring.todoList.domain.user.service.UserService;
import katachi.spring.todoList.form.DeleteForm;

/**
 * 削除処理画面コントローラー
 * 
 * @author S.Tsujino
 *
 */
@Controller
@RequestMapping("/user")
public class TaskDeleteController {
	// データベースアクセス処理クラス
	@Autowired
	private UserService userService;
	// モデルクラス
	@Autowired
	private ModelMapper modelMapper;
	// Viewの各項目を表示するクラス
	@Autowired
	private MessageSource messageSource;
	// セッション用クラス
	@Autowired
	private HttpSession session;
	// セッション用モデルクラス(作業リストのID)
	@Autowired
	private SessionData sessionData;

	// セッション初期化
	@ModelAttribute("sessionData")
	private SessionData getData() {
		return new SessionData();
	}

	/**
	 * 作業削除ページ
	 * 
	 * @param id     指定した項目のID
	 * @param model
	 * @param locale
	 * @param form   削除画面用のフォーム
	 * @return 削除処理画面へ
	 */
	@GetMapping("/delete")
	public String getDelete(int id, Model model, Locale locale, @ModelAttribute DeleteForm form) {
		// セッションデータにIDを格納
		sessionData.setId(id);
		// 作業内容一覧画面で選んだ項目のIDを検索して呼び出してuserに格納
		MUser user = userService.getTaskOne(id);
		// 呼び出したtaskを表示させるために呼び出した項目をformに格納
		form = modelMapper.map(user, DeleteForm.class);
		// 呼び出した作業内容をパラメータに保存
		model.addAttribute("deleteForm", form);
		// タイトルとヘッダー部分をパラメータに保存
		model.addAttribute("title", messageSource.getMessage("delete.title", null, locale));
		return "user/delete";
	}

	/**
	 * 作業内容削除処理
	 * 
	 * @return 削除処理後に直前のページ遷移
	 */
	@PostMapping(value = "/delete", params = "delete")
	public String deleteTaskOne() {
		// セッションデータのIDを呼び出し
		int id = sessionData.getId();
		// 指定した項目のIDの作業内容更新処理へ
		userService.deleteTaskOne(id);
		// 検索フォームに入力していた場合の遷移先分岐メソッド
		return back();
	}

	/**
	 * 削除ページから一覧ページへ
	 * 
	 * @return 検索結果がnullである場合に分岐して作業内容一覧と検索結果一覧に遷移先を決める
	 */
	@PostMapping(value = "/delete", params = "cancel")
	public String back() {
		// セッションの検索内容の呼び出し
		String search = (String) session.getAttribute("search");
		// 検索フォームに入力していた場合の遷移先分岐
		if (search != null) {
			// 検索画面へ移動
			return "redirect:/user/search";
		}
		// 作業内容一覧へ移動
		return "redirect:/user/list";
	}
}
