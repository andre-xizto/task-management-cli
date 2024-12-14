package dev.buskopan.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.buskopan.model.Task;
import dev.buskopan.type_adapter.TaskType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TaskManager {

    private Gson gson;
    private static final String DIRECTORY_PATH = "TaskCLI";
    private static final String FILE_PATH = ".task-cli.json";

    public TaskManager() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Task.class,new TaskType())
                .create();
        checkAndCreateFiles();
    }

    private void checkAndCreateFiles() {
        String userHomePath = System.getProperty("user.home");
        Path directoryPath = getDirectoryPath(userHomePath);
        Path filePath = getFilePath(directoryPath);

        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
                System.out.println("Diretorio criado " + directoryPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
                System.out.println("Arquivo criado "+filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Path getFilePath(Path directoryPath) {
        return directoryPath.resolve(FILE_PATH);
    }

    private Path getDirectoryPath(String parentPath) {
        return Paths.get(parentPath, DIRECTORY_PATH);
    }
}
