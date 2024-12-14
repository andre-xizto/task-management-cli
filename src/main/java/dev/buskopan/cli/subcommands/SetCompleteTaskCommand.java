package dev.buskopan.cli.subcommands;

import dev.buskopan.cli.TaskManagementCLI;
import dev.buskopan.manager.TaskManager;
import dev.buskopan.task_enum.TaskStatus;
import picocli.CommandLine;

@CommandLine.Command(
        name = "complete",
        description = "sets task's status as completed"
)
public class SetCompleteTaskCommand implements Runnable{

    @CommandLine.Parameters(
            index = "0",
            description = "Task id"
    )
    private long id;

    @Override
    public void run() {
        TaskManager taskManager = TaskManagementCLI.taskManager;
        boolean result = taskManager.updateTask(id, TaskStatus.COMPLETE);

        if (!result) {
            System.out.println("Task not found with this id");
            return;
        }

        System.out.println("Task marked as complete");
    }
}
