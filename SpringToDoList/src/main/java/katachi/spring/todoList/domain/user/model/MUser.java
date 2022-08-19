package katachi.spring.todoList.domain.user.model;

import java.util.Date;

import lombok.Data;

@Data
public class MUser {
	private int id;
	private String userId;
	private String userName;
	private String password;
	private String content;
	private Date createdDate;
	private Date endDate;
	private Date completeDate;
	private int completed;
	private String role;
}
