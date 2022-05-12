package katachi.spring.todoList.application.service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class UserApplicationService {
	@Autowired
	private MessageSource messageSource;

	/**使用していない*/
	public Map<String, Integer> getTitleMap(Locale locale) {
		Map<String, Integer> titleMap = new LinkedHashMap<>();
		String login = messageSource.getMessage("login", null, locale);
		String list = messageSource.getMessage("list", null, locale);
//		String entry = messageSource.getMessage("entry", null, locale);
//		String edit = messageSource.getMessage("edit", null, locale);
//		String delete = messageSource.getMessage("delete", null, locale);
//		String search = messageSource.getMessage("search", null, locale);
		titleMap.put(login, 1);
		titleMap.put(list, 2);
//		titleMap.put(entry, 3);
//		titleMap.put(edit, 4);
//		titleMap.put(delete, 5);
//		titleMap.put(search, 6);

		return titleMap;
	}
}
