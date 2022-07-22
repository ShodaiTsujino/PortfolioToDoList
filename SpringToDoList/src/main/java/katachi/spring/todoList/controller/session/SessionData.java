package katachi.spring.todoList.controller.session;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Data
@Component
@SessionScope
public class SessionData implements Serializable{
    private static final long serialVersionUID = 1L;
    private String search;
    }
