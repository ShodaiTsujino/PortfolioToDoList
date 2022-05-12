package katachi.spring.todoList.aspect;

import java.sql.SQLSyntaxErrorException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 例外処理クラスs
 * @author S.Tsujino
 *
 */
@ControllerAdvice
@Component
public class GlobalControllAdvice {
	/**
	 * メッセージクラス
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * データベース関連の例外処理
	 * @param e
	 * @param model
	 * @param locale
	 * @return データベースエラーページへ遷移
	 */
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e,Model model, Locale locale) {
		//空文字をセット
		model.addAttribute("error","");

		//メッセージをModelに登録
		model.addAttribute("message","DataAccessExceptionが発生しました");

		//HTTPのエラーコード(500)をModelに登録
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR);
		model.addAttribute("title", messageSource.getMessage("error.title", null, locale));

		return "error";
	}

	/**
	 * その他の例外処理
	 * @param e
	 * @param model
	 * @param locale
	 * @return その他の例外エラーページへ
	 */
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e,Model model, Locale locale) {

		//空文字をセット
		model.addAttribute("error", "");

		//メッセージをModelに登録
		model.addAttribute("message", "Exceptionが発生しました");

		//HTTPのエラーコード(500)をModelに登録
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR );
		model.addAttribute("title", messageSource.getMessage("error.title", null, locale));
		return "error";
	}
	/**
	 * 
	 * @param e
	 * @param model
	 * @param locale
	 * @return
	 */
	@ExceptionHandler(SQLSyntaxErrorException.class)
	public String exceptionHandler(SQLSyntaxErrorException e,Model model, Locale locale) {

		//空文字をセット
		model.addAttribute("error", "");

		//メッセージをModelに登録
		model.addAttribute("message", "Exceptionが発生しました");

		//HTTPのエラーコード(500)をModelに登録
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR );
		model.addAttribute("title", messageSource.getMessage("error.title", null, locale));
		return "error";
	}
}
