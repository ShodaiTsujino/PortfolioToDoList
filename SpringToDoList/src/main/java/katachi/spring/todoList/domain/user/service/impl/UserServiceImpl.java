package katachi.spring.todoList.domain.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.todoList.domain.user.model.LoginUser;
import katachi.spring.todoList.domain.user.model.MUser;
import katachi.spring.todoList.domain.user.service.UserService;
import katachi.spring.todoList.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	// タスク取得
	@Override
	public List<MUser> getTaskList(String search) {
		return mapper.findMany(search);
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

	// タスクの論理削除
	@Override
	public void deleteTaskOne(int id) {
		mapper.deleteOne(id);
	}

	//ログインユーザー照合
	@Override
	public LoginUser getLoginUser(String userId) {
		return  mapper.findLoginUser(userId);
	}

	@Override
	public void completeTaskOne(int id) {
		mapper.completeOne(id);
	}




}
