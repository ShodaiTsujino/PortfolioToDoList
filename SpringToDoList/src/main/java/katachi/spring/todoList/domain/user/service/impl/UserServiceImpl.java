package katachi.spring.todoList.domain.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.todoList.domain.user.model.LoginUser;
import katachi.spring.todoList.domain.user.model.MUser;
import katachi.spring.todoList.domain.user.service.UserService;
import katachi.spring.todoList.form.UpdateForm;
import katachi.spring.todoList.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	// ユーザー取得
	@Override
	public List<MUser> getTaskList() {
		return mapper.findMany();
	}

	// ユーザー取得
	@Override
	public List<MUser> getUsers() {
		return mapper.findManyUser();
	}

	// 作業内容登録
	@Override
	public void addTaskOne(MUser user) {
		user.setCreatedDate(new Date());
		mapper.insertOne(user);
	}

	// 1件ユーザー取得
	@Override
	public MUser getTaskOne(int id) {
		return mapper.findTaskOne(id);
	}

	//作業内容登録
	@Override
	public void updateTaskOne(MUser user) {
		mapper.updateOne(user);
	}

	//完了日のフォーマット
	@Override
	public void completeDateFormat(UpdateForm form) {
		Date today = new Date(); // 今日の日付生成
		if (form.getCompleted() != 0) { //完了にチェックが入って0じゃなかった場合
			if (form.getCompleteDate() == null) {// 完了日がnullだった場合
				form.setCompleteDate(today);// 完了日に今日の日付を入れる
			} // 完了日がnullじゃなかった場合は何もしない
			form.setCompleteDate(form.getCompleteDate());
		}
	}

	// タスクの論理削除
	@Override
	public void deleteTaskOne(int id) {
		mapper.deleteOne(id);
	}
	
	//タスクの検索
	@Override
	public List<MUser> getSearchTaskList(String content) {
		return mapper.searchMany(content);
	}
	
	//ログインユーザー照合
	@Override
	public LoginUser getLoginUser(String userId) {
		return  mapper.findLoginUser(userId);
	}

}
