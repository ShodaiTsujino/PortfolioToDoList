package katachi.spring.todoList.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import katachi.spring.todoList.domain.user.model.LoginInfo;
import katachi.spring.todoList.domain.user.service.UserService;

/**
 * ログイン処理クラス
 * @author S.Tsujino
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	/**
	 * データベースアクセスクラス
	 */
	@Autowired
	private UserService userService;

	/**
	 * ログインユーザー照合
	 */
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// ユーザー情報取得
		LoginInfo loginUser = userService.getLoginUser(userId);
		// ユーザーが存在しない場合
		if (loginUser == null) {
			throw new UsernameNotFoundException("user not found");
		}
		//独自UserDetailsServiceへ
		return loginUser;
	}
}
