package org.example.repository.task;

import org.example.model.task.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTaskList();
    Task createNewTask(Task task);
    Task updateTaskDescription(Task task);
    void taskToInProgress(long id);
    void canceledTask(long id);
    void completedTask(long id);
    void raiseTaskPriority(long id);
    void deleteTask(long id);
}
