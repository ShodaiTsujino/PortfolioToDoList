package katachi.spring.todoList.domain.user.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Event {
    private String id;
    private String title;
    private Date start;
    private Date end;
    private String color;
}
