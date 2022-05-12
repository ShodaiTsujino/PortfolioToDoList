package katachi.spring.todoList.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UpdateForm {
	@NotBlank
	@Length(min = 0, max = 100)
	private String content;
	private String userId;
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date endDate;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date completeDate;
	private int completed;
}