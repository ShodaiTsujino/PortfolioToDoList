package katachi.spring.todoList.controller.session;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SessionCompData implements Serializable{
    private static final long serialVersionUID = 1L;
    Date sessionCompDate;
    }
