package dev.buskopan.cli.subcommands;

import dev.buskopan.cli.TaskManagementCLI;
import dev.buskopan.manager.TaskManager;
import dev.buskopan.model.Task;
import dev.buskopan.task_enum.TaskStatus;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "list",
        description = "lists tasks"
)
public class ListTasksCommand implements Runnable{

    @CommandLine.Option(
            names = {"-todo", "-t"}
    )
    private boolean onlyTodo;
    @CommandLine.Option(
            names = {"-completed", "-c"}
    )
    private boolean onlyCompleted;
    @CommandLine.Option(
            names = {"-inprogress", "-i"}
    )
    private boolean onlyInProgress;

    @Override
    public void run() {
        TaskManager taskManager = TaskManagementCLI.taskManager;
        List<Task> tasks = taskManager.listTasks();
        List<Task> result;

        result = onlyTodo ? tasks.stream().filter(e -> e.getStatus().equals(TaskStatus.TODO)).toList()
            : (onlyCompleted ? tasks.stream().filter(e -> e.getStatus().equals(TaskStatus.COMPLETE)).toList()
                : (onlyInProgress ? tasks.stream().filter(e -> e.getStatus().equals(TaskStatus.IN_PROGRESS)).toList()
                    : tasks));

        result.forEach(System.out::println);
    }
}
