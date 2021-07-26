package nice.dto;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

//import nice.models.Task;
import nice.models.TaskRecord;

public class TaskDTO {
    private long id;
    private long program;
    private String name;
    private long createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadline;
    private long modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedTime;
    private String status;

    @Column(length = 65450, columnDefinition = "text")
    private String description;

    private TaskDTO task;
    private long[] user;
    private List<TaskRecord> taskList;

    public TaskDTO getTask() {
        return task;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }

    public List<TaskRecord> getTaskList(){
        return taskList;
    }

    public void setTaskList(List<TaskRecord> taskList){
        this.taskList = taskList;
    }

    public long[] getUser() {
        return user;
    }

    public void setTrainee(long[] user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public long getProgram() {
        return program;
    }

    public String getName() {
        return name;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public long getModifiedBy() {
        return modifiedBy;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProgram(long program) {
        this.program = program;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setModifiedBy(long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
