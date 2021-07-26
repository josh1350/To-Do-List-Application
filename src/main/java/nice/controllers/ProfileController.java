package nice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import nice.models.User;
import nice.services.TaskService;
import nice.services.UserService;

import java.security.Principal;


@Controller
public class ProfileController {

    private UserService userService;
    private TaskService taskService;

    @Autowired
    public ProfileController(UserService userService, TaskService taskService){
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model, Principal principal){
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("taskOwned", taskService.findByOwnerOrderByDateDesc(user));
        return "views/profile";
    }
    
}
