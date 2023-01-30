package org.example.valid;

import org.example.exception.ConflictException;
import org.example.model.task.TaskStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskValidation {

    private static final String ERROR_TEXT = "Ошибка.";
    private static final String COMPLETED_TEXT = "Задание выполнено.";
    private static final String CANCELED_TEXT = "Заданее отменено.";
    private static final String IN_PROGRESS_TEXT = "Задание в работе.";

    public static void validationTaskToInProgress(TaskStatus taskStatus) {
        switch(taskStatus) {
            case IN_PROGRESS:
                throw new ConflictException(ERROR_TEXT, IN_PROGRESS_TEXT);
            case CANCELED:
                throw new ConflictException(ERROR_TEXT, CANCELED_TEXT);
            case COMPLETED:
                throw new ConflictException(ERROR_TEXT, COMPLETED_TEXT);
        }
    }

    public static void validationCanceledAndCompletedTask(TaskStatus taskStatus) {
        switch(taskStatus) {
            case CANCELED:
                throw new ConflictException(ERROR_TEXT, CANCELED_TEXT);
            case COMPLETED:
                throw new ConflictException(ERROR_TEXT, COMPLETED_TEXT);
        }
    }
}
