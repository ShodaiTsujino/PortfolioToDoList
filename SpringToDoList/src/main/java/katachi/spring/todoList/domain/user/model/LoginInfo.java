package katachi.spring.todoList.domain.user.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * ログイン用のエンティティ
 * UserDetailsを使用して独自のログインユーザー情報(ユーザーID、パスワード、ユーザー名、認証権限)を保持
 * @author S.Tsujino
 *
 */
@Data
public class LoginInfo implements UserDetails{

	private static final long serialVersionUID = 1L;
	private String username;
	private String viewName;
	private String password;

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	//権限リスト作成
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
		authorities.add(authority);
      return authorities;
    }

	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}
}