package nice.controllers;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nice.models.Task;
import nice.models.User;
import nice.services.TaskService;
import nice.services.UserService;

@Controller
public class AssignmentController {

    private UserService userService;
    private TaskService taskService;

    @Autowired
    public AssignmentController(UserService userService, TaskService taskService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/assignment")
    public String showAssignments(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("freeTasks", taskService.findFreeTasks());
        return "forms/assignment";
    }

    @GetMapping("/assignment/assign/{userId}")
    public String showAssignmentForm(Model model, @PathVariable Long userId) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("selectedUser", userService.getUserById(userId));
        model.addAttribute("freeTasks", taskService.findFreeTasks());
        return "forms/assignment";
    }

    @GetMapping("/assignment/{userId}/{taskId}")
    public String assignTaskToUser(Model model, @PathVariable Long userId, @PathVariable Long taskId) {
        Task selectedTask = taskService.getTaskById(taskId);
        User selectedUser = userService.getUserById(userId);
        taskService.assignTaskToUser(selectedTask, selectedUser);
        return "redirect/assignment/" + userId;
    }

    @GetMapping("/assignment/unassign/{userid}/{taskId}")
    public String unassignTaskFromUser(@PathVariable Long userId, @PathVariable Long taskId) {
        Task selectedTask = taskService.getTaskById(taskId);
        taskService.unassignTask(selectedTask);
        return "redirect:/assignment/" + userId;
    }

}
