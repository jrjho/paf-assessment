package ibf2022.paf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;

@Repository
public class TaskRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String INSERT_SINGLE_TASK_SQL = "insert into task(description, priority, due_date, user_id) values (?,?,?,?)";

    private final String GET_USER_ID_FROM_USERNAME = "select user_id from user where username = ?";



    public Boolean insertTask(Task task, String username) {

        String userId = jdbcTemplate.queryForObject(GET_USER_ID_FROM_USERNAME, String.class, username);

        int result = jdbcTemplate.update(INSERT_SINGLE_TASK_SQL, task.getDescription(), task.getPriority(),
                task.getDueDate(), userId);

        return result > 0 ? true : false;
    }
}
