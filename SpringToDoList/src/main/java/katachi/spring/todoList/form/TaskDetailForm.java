package katachi.spring.todoList.form;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import katachi.spring.todoList.domain.user.service.UserService;
import lombok.Data;

@Data
public class TaskDetailForm {

	@Autowired
	private UserService userService;

	@NotBlank
	@Length(min = 0, max = 100)
	private String content;
	@NotNull
	private String userId;

	@NotNull
	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date endDate;
	private String completeDate;

//	@DateTimeFormat(pattern = "yyyy/MM/dd")
//	public Date getCompleteDate() {
//		if (completeDate != null) { // 完了日がnullじゃなかった場合
//			if (completeDate == "1") {//完了日が1の場合は今日の日付
//				Date today = new Date(); // 今日の日付生成
//				return today;
//			}
//			Date date = userService.completeDateFormat(completeDate); //文字列を日付へフォーマット
//			return date;//完了日が存在する場合は更新無し
//		}
//		return null;
//	}
}