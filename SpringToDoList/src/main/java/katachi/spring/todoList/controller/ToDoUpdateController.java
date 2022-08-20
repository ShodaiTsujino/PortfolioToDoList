package katachi.spring.todoList.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import katachi.spring.todoList.domain.user.model.MUser;
import katachi.spring.todoList.domain.user.service.UserService;
import katachi.spring.todoList.form.UpdateForm;

/**
 * 作業内容更新ページ
 *
 * @author S.Tsujino
 *
 */
@Controller
@RequestMapping("/user")
public class ToDoUpdateController {
	// データベースアクセス処理クラス
	@Autowired
	private UserService userService;
	// モデルクラス
	@Autowired
	private ModelMapper modelMapper;
	// Viewに表示する項目を呼び出すための項目
	@Autowired
	private MessageSource messageSource;

	/**
	 * Viewで表示するメッセージを常に取得
	 * @param model
	 * @param locale
	 */
	@ModelAttribute
	public void setVIewDisp(Model model, Locale locale) {
		// データベースのユーザー名を呼び出し
		List<MUser> userList = userService.getUsers();
		// Viewで表示するユーザー一覧をパラメーターに格納
		model.addAttribute("userList", userList);
		// Viewで表示するタイトルとヘッダー部分パラメータを格納
		model.addAttribute("title", messageSource.getMessage("update.title", null, locale));
	}

	/**
	 * 作業更新ページを表示
	 *
	 * @param id     更新する対象の作業項目ID
	 * @param model
	 * @param form   Viewのフォームクラス
	 * @return 作業更新画面へ
	 */
	@GetMapping("/update/{id}")
	public String getUpdate(
			@PathVariable("id") int id,
			Model model,
			@ModelAttribute UpdateForm form
			) {
		// 作業一覧ページで選んだ項目のIDの作業内容を検索して呼び出し
		MUser user = userService.getToDoOne(id);
		// 呼び出した作業内容を表示させるために呼び出した項目をformに保存
		form = modelMapper.map(user, UpdateForm.class);
		model.addAttribute("updateForm", form);
		return "user/update";
	}

	/**
	 * 作業内容更新処理
	 *
	 * @param form          Viewのフォームクラス,バリデーション用
	 * @param bindingResult バリデーション結果
	 * @param id            対象の作業内容のID
	 * @param search		セッションの検索内容
	 * @param redirectAttributes リダイレクトクラス
	 * @return 内容更新後に直前の画面に遷移
	 */
	@PostMapping(value = "/update/{id}", params = "update")
	public String updateToDoOne(
			@PathVariable("id") int id
			,@ModelAttribute @Validated UpdateForm form
			,@SessionAttribute(name="search", required = false) String search
			,RedirectAttributes redirectAttributes
			,BindingResult bindingResult
			) {
		System.out.println("aaaawa");
		// バリデーションチェック
		if (bindingResult.hasErrors()) {
			// NG:ユーザー更新画面に戻ります
			return "user/update";
		}
		if (form.getCompleted() != 0) {
			form.setCompleteDate(new Date());
		}
		// formをMUserクラスに変換
		MUser user = modelMapper.map(form, MUser.class);
		user.setId(id);
		// 作業内容更新処理
		userService.updateToDoOne(user);
		//パラメータに検索結果を格納
		redirectAttributes.addAttribute("search", search);
		// 作業内容一覧へ移動
		return "redirect:/user/list";
	}
}
