package katachi.spring.todoList.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import katachi.spring.todoList.domain.user.model.LoginInfo;
import katachi.spring.todoList.domain.user.model.MUser;

@Mapper
public interface UserMapper {

	//ユーザー登録
	public int insertOne(MUser user);

	// タスクリスト取得
	public List<MUser>findMany(String search);
	// ユーザーリスト取得
	public List<MUser>findManyUser();

	// タスク取得(1件)
	public MUser findToDoOne(int id);

	// タスク更新(1件)
	public void updateOne(MUser user);

	//論理削除(１件)
	public void deleteOne(int id);

	//完了処理
	public void completeOne(int id, Date date);

	//ログインユーザー取得
	public LoginInfo findLoginUser(String userId);

	//一覧画面から完了処理
	public void completeOne(int id);
}
