package org.example.repository.task;

import org.example.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository  extends JpaRepository<Task, Long> {

    List<Task> findAllOrderByCreationOnUpdated();
}
