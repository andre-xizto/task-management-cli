package dev.buskopan.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dev.buskopan.model.Task;
import dev.buskopan.task_enum.TaskStatus;
import dev.buskopan.type_adapter.TaskType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

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
        Path directoryPath = getDirectoryPath();
        Path filePath = getFilePath();

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

    private void saveTasks(List<Task> tasks) {
        Path filePath = getFilePath();
        File jsonFile = filePath.toFile();
        try (FileWriter writer = new FileWriter(jsonFile)) {
            gson.toJson(tasks,writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> listTasks() {
        Path filePath = getFilePath();
        File jsonFile = filePath.toFile();

        try (Reader reader = new FileReader(jsonFile)) {
            return gson.fromJson(reader, new TypeToken<List<Task>>(){}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public long createTask (String name) {
        List<Task> tasks = listTasks();
        long id = tasks.isEmpty() ? 1 : tasks.getLast().getId() + 1;

        Task task = new Task(name);
        task.setId(id);

        tasks.add(task);
        saveTasks(tasks);

        return id;

    }

    public boolean removeTask(long id) {
        List<Task> tasks = listTasks();
        Optional<Task> optionalTask = tasks.stream().filter(e -> e.getId() == id).findFirst();

        if (optionalTask.isEmpty()) {
            return false;
        }

        tasks.remove(optionalTask.get());
        saveTasks(tasks);
        return true;
    }

    public boolean updateTask(long id, TaskStatus status) {
        List<Task> tasks = listTasks();
        Optional<Task> optionalTask = tasks.stream().filter(e -> e.getId() == id).findFirst();

        if (optionalTask.isEmpty()) {
            return false;
        }

        optionalTask.get().setStatus(status);
        saveTasks(tasks);
        return true;
    }

    private Path getFilePath() {
        Path directoryPath = getDirectoryPath();
        return directoryPath.resolve(FILE_PATH);
    }

    private Path getDirectoryPath() {
        String userHome = System.getProperty("user.home");
        return Paths.get(userHome, DIRECTORY_PATH);
    }
}
