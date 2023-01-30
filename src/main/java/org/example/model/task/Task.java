package org.example.model.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Ошибка валидации: нужно заполнить описание.")
    @Column(name = "description")
    private String description;
    @Column(name = "creation_on")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime creationOn;
    @Column(name = "creation_on_updated")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime creationOnUpdated;
    @Enumerated(EnumType.ORDINAL)
    private TaskStatus status;

    public Task createNewTask() {
        this.creationOn = LocalDateTime.now();
        this.creationOnUpdated = LocalDateTime.now();
        this.status = TaskStatus.NEW;
        return this;
    }
}
