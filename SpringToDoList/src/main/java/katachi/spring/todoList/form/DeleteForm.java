package katachi.spring.todoList.form;

import java.util.Date;

import lombok.Data;
@Data
public class DeleteForm {
	private String content;
	private String userName;
	private Date endDate;
	private Date completeDate;
}
