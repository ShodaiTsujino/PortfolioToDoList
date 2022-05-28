package katachi.spring.todoList.domain.user.service;

import java.util.List;

import katachi.spring.todoList.domain.user.model.LoginUser;
import katachi.spring.todoList.domain.user.model.MUser;
import katachi.spring.todoList.form.UpdateForm;

public interface UserService {

	/*ユーザー登録*/
	public void addTaskOne(MUser user);
	/* タスク取得 */
	public List<MUser> getTaskList(String search);
	/* ユーザー取得 */
	public List<MUser> getUsers();
	/*ユーザー取得(1件)*/
	public MUser getTaskOne(int id);

	/*ユーザー更新(1件)*/
	public void updateTaskOne(MUser user);

	/*完了チェックした日の日付生成*/
	public void completeDateFormat(UpdateForm form);
	//タスク論理削除
	public void deleteTaskOne(int id);
	//タスク検索
	public List<MUser> getSearchTaskList(String search);
	//ログイン処理
	public LoginUser getLoginUser(String userId);
}
