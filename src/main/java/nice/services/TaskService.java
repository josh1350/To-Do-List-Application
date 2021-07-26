package nice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nice.models.Task;
import nice.models.TaskDao;
import nice.models.User;

@Service
public class TaskService {

    private TaskDao taskRepository;

    @Autowired
    public TaskService(TaskDao taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    public void updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.getById(id);
        task.setName(updatedTask.getName());
        task.setDescription(updatedTask.getDescription());
        task.setDate(updatedTask.getDate());
        taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional
    public List<Task> findByOwnerOrderByDateDesc(User user) {
        return taskRepository.findByOwnerOrderByDateDesc(user);
    }

    @Transactional
    public void setTaskCompleted(Long id) {
        Task task = taskRepository.getById(id);
        task.setCompleted(true);
        taskRepository.save(task);
    }

    @Transactional
    public void setTaskNotCompleted(Long id) {
        Task task = taskRepository.getById(id);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    @Transactional
    public List<Task> findFreeTasks() {
        return taskRepository.findAll()
                .stream()
                .filter(task -> task.getOwner() == null && !task.isCompleted())
                .collect(Collectors.toList());

    }

    @Transactional
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Transactional
    public void assignTaskToUser(Task task, User user) {
        task.setOwner(user);
        taskRepository.save(task);
    }

    @Transactional
    public void unassignTask(Task task) {
        task.setOwner(null);
        taskRepository.save(task);
    }

}
