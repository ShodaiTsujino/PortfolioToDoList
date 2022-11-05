package katachi.spring.todoList.domain.user.service;

import java.util.List;

import katachi.spring.todoList.domain.user.model.Event;
import katachi.spring.todoList.domain.user.model.LoginInfo;
import katachi.spring.todoList.domain.user.model.MUser;

public interface UserService {

	/*ユーザー登録*/
	public void addToDoOne(MUser user);

	/* タスク取得 */
	public List<MUser> getToDoList(String search);

	/* ユーザー取得 */
	public List<MUser> getUsers();

	/*ユーザー取得(1件)*/
	public MUser getToDoOne(int id);

	/*ユーザー更新(1件)*/
	public void updateToDoOne(MUser user);

	//タスク論理削除
	public void deleteToDoOne(int id);
	//ログイン処理
	public LoginInfo getLoginUser(String userId);

	//リスト画面から完了処理
	public void completeToDoOne(int id);

	//カレンダー内容取得
	public List<Event> getAllEvent();
}
