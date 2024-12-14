package dev.buskopan.cli.subcommands;

import dev.buskopan.cli.TaskManagementCLI;
import dev.buskopan.manager.TaskManager;
import picocli.CommandLine;

@CommandLine.Command(
        name = "remove",
        description = "removes a task"
)
public class RemoveTaskCommand implements Runnable{

    @CommandLine.Parameters(
            index = "0",
            description = "Task id"
    )
    private long id;

    @Override
    public void run() {
        TaskManager taskManager = TaskManagementCLI.taskManager;
        boolean result = taskManager.removeTask(id);
        if (!result) {
            System.out.println("Task not found with this id");
            return;
        }
        System.out.println("Task removed!");
    }
}
