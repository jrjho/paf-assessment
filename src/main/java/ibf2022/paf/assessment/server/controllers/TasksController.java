package ibf2022.paf.assessment.server.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.services.TodoService;


@Controller
@RequestMapping
public class TasksController {

    private int count = 0;

    @Autowired
    TodoService todoSvc;

    @PostMapping("/task")
    public ModelAndView postTask(@RequestBody MultiValueMap<String, String> formData, Model model) {

        String username = formData.getFirst("username");
        List<Task> tasks = new ArrayList<>();

        formData.forEach((key, value) -> {
            System.out.println(key + " " + value);
            this.count += 1;
        });

        count = count - 1;
        count = count / 3;
       

        for (int i = 0; i < count; i++) {
            Task task = new Task();
            task.setDescription(formData.getFirst("description-" + i));
            task.setPriority(Integer.parseInt(formData.getFirst("priority-" + i)));
            task.setDueDate(formData.getFirst("dueDate-" + i));
            tasks.add(task);
        }

        Boolean check = todoSvc.upsertTask(tasks, username);

        if (check) {
            Integer taskCount = count;
            count = 0;

            var mav = new ModelAndView();
            mav.addObject("taskCount", taskCount);
            mav.addObject("username", username);
            mav.setViewName("result");
            mav.setStatus(HttpStatus.valueOf(200));

            return mav;
        }else {
            count = 0;
            var mav = new ModelAndView();
            mav.setStatus(HttpStatus.valueOf(500));
            mav.setViewName("error");
            return mav;
        }

    }

}
