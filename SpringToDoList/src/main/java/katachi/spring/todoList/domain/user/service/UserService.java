package katachi.spring.todoList.domain.user.service;

import java.util.List;

import katachi.spring.todoList.domain.user.model.LoginUser;
import katachi.spring.todoList.domain.user.model.MUser;

public interface UserService {

	/**
	 * 作業内容登録
	 * 
	 * @param user 登録内容
	 */
	public void addTaskOne(MUser user);

	/**
	 * タスク取得
	 * 
	 * @return 作業内容一覧表示へ
	 */
	public List<MUser> getTaskList();

	/**
	 * ユーザー名取得
	 * 
	 * @return 担当者一覧表示へ
	 */
	public List<MUser> getUsers();

	/**
	 * ユーザー取得(1件)
	 * 
	 * @param id
	 * @return 各処理で表示する作業内容(1件)
	 */
	public MUser getTaskOne(int id);

	/**
	 * 作業内容更新(1件)
	 * 
	 * @param user 更新内容
	 */
	public void updateTaskOne(MUser user);

	/**
	 * タスク論理削除
	 * @param id 削除対象の登録ID(1件)
	 */
	public void deleteTaskOne(int id);

	/**
	 * タスク検索
	 * @param content 作業内容の項目名
	 * @return 対象項目名の作業内容一覧
	 */
	public List<MUser> getSearchTaskList(String content);

	/**
	 *  ログイン処理
	 * @param userId ログインユーザー
	 * @return 認証処理へ
	 */
	public LoginUser getLoginUser(String userId);
}
