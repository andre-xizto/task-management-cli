package dev.buskopan.cli.subcommands;

import dev.buskopan.cli.TaskManagementCLI;
import dev.buskopan.manager.TaskManager;
import picocli.CommandLine;

@CommandLine.Command(
        name = "add",
        description = "adds a task"
)
public class AddTaskCommand implements Runnable{

    @CommandLine.Parameters(
            description = "Task name"
    )
    private String taskName;

    @Override
    public void run() {
        TaskManager taskManager = TaskManagementCLI.taskManager;
        long id = taskManager.createTask(taskName);
        System.out.println("Task create, id: " + id);
    }
}
