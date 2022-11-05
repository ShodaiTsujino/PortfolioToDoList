package katachi.spring.todoList.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import katachi.spring.todoList.domain.user.model.Event;
import katachi.spring.todoList.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RestCalendarController {

	/**
	 * データベースアクセスクラス
	 */
	@Autowired
	private UserService service;

	@GetMapping("/schedule/event")
	public String getCalendar(Authentication loginUser) {
		String jsonMsg = null;
		try {
			List<Event> events = service.getAllEvent();
			// FullCalendarにエンコード済み文字列を渡す
			ObjectMapper mapper = new ObjectMapper();
			jsonMsg = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);
		} catch (IOException ioex) {
			System.out.println(ioex.getMessage());
		}
		return jsonMsg;
	}
	@GetMapping("/schedule/search?content={content}&userId={userId}&endDate={endDate}&completed={completed}")
	public String getCalendarSearch(@PathVariable String content,@PathVariable String userId,@PathVariable String endDate,@PathVariable String completed) {
		String jsonMsg = null;
		System.out.println(content);
		try {
			List<Event> events = service.getAllEvent();
			// FullCalendarにエンコード済み文字列を渡す
			ObjectMapper mapper = new ObjectMapper();
			jsonMsg = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);
		} catch (IOException ioex) {
			System.out.println(ioex.getMessage());
		}
		return jsonMsg;
	}
}
