package dev.buskopan.cli;

import dev.buskopan.cli.subcommands.*;
import dev.buskopan.manager.TaskManager;
import picocli.CommandLine;

@CommandLine.Command(
        name = "task-cli",
        subcommands = {
                ListTasksCommand.class,
                AddTaskCommand.class,
                RemoveTaskCommand.class,
                SetCompleteTaskCommand.class,
                SetInProgressCommand.class,
                SetToDoCommand.class
        }
)
public class TaskManagementCLI implements Runnable{

    public static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        new CommandLine(new TaskManagementCLI()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("Use the commands: list, add, remove, complete, todo, inprogress");
    }
}
