package ibf2022.paf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.exceptions.Exceptions;
import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

@Service
public class TodoService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    TaskRepository taskRepo;

    @Transactional(rollbackFor = Exceptions.class)
    public Boolean upsertTask(List<Task> tasks, String username) throws Exceptions {

        Boolean userExists = false;

        Optional<User> checkUser = userRepo.findUserByUsername(username);

        if (checkUser.isEmpty()) {  
            User user = new User();
            user.setUsername(username);
            String result = userRepo.insertUser(user);

            if (result != null)
                userExists = true;
            else
                return false;

        } else {
            userExists = true;
        }

        if (userExists) {

            for (Task task : tasks) {
                Boolean result = taskRepo.insertTask(task, username);
                if (!result)
                    throw new Exceptions("Task could not be inserted");
            }
            return true;

        } else
            throw new Exceptions("User does not exist and could not be created");

    }
}
