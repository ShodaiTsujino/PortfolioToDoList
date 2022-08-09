package katachi.spring.todoList.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.todoList.domain.user.model.LoginInfo;
import katachi.spring.todoList.domain.user.model.MUser;
import katachi.spring.todoList.domain.user.service.UserService;
import katachi.spring.todoList.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	// タスク取得
	@Override
	public List<MUser> getToDoList(String search) {
		return mapper.findMany(search);
	}

	// ユーザー取得
	@Override
	public List<MUser> getUsers() {
		return mapper.findManyUser();
	}

	// 作業内容登録
	@Override
	public void addToDoOne(MUser user) {
		mapper.insertOne(user);
	}

	// 1件ユーザー取得
	@Override
	public MUser getToDoOne(int id) {
		return mapper.findToDoOne(id);
	}

	//作業内容登録
	@Override
	public void updateToDoOne(MUser user) {
		mapper.updateOne(user);
	}

	// タスクの論理削除
	@Override
	public void deleteToDoOne(int id) {
		mapper.deleteOne(id);
	}

	//ログインユーザー照合
	@Override
	public LoginInfo getLoginUser(String userId) {
		return  mapper.findLoginUser(userId);
	}

	@Override
	public void completeToDoOne(int id) {
		mapper.completeOne(id);
	}
}
