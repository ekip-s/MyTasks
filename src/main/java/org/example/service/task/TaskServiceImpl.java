package org.example.service.task;

import org.example.exception.NotFoundException;
import org.example.model.task.Task;
import org.example.model.task.TaskStatus;
import org.example.repository.task.TaskService;
import org.example.repository.task.TasksRepository;
import org.example.valid.TaskValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final TasksRepository tasksRepository;

    public TaskServiceImpl(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }


    @Override
    public List<Task> getTaskList() {
        return tasksRepository.findAllOrderByCreationOnUpdated();
    }


    @Override
    @Transactional
    public Task createNewTask(Task task) {
        return tasksRepository.save(task.createNewTask());
    }

    @Override
    @Transactional
    public Task updateTaskDescription(Task task) {
        Task taskDB = getTaskById(task.getId());
        taskDB.setDescription(task.getDescription());
        return tasksRepository.save(taskDB);
    }

    @Override
    @Transactional
    public void taskToInProgress(long id) {
        Task task = getTaskById(id);
        TaskValidation.validationTaskToInProgress(task.getStatus());
        task.setStatus(TaskStatus.IN_PROGRESS);
        tasksRepository.save(task);
    }

    @Override
    @Transactional
    public void canceledTask(long id) {
        Task task = getTaskById(id);
        TaskValidation.validationCanceledAndCompletedTask(task.getStatus());
        task.setStatus(TaskStatus.CANCELED);
        tasksRepository.save(task);
    }

    @Override
    @Transactional
    public void completedTask(long id) {
        Task task = getTaskById(id);
        TaskValidation.validationCanceledAndCompletedTask(task.getStatus());
        task.setStatus(TaskStatus.COMPLETED);
        tasksRepository.save(task);
    }

    @Override
    @Transactional
    public void raiseTaskPriority(long id) {
        Task task = getTaskById(id);
        TaskValidation.validationCanceledAndCompletedTask(task.getStatus());
        task.setCreationOnUpdated(LocalDateTime.now());
        tasksRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteTask(long id) {
        getTaskById(id);
        tasksRepository.deleteById(id);
    }

    private Task getTaskById(long id) {
        return tasksRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Неправильные данные.",
                        "Ошибка: нет задания с Id=" + id + "."));
    }
}
