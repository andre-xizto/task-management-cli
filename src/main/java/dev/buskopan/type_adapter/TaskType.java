package dev.buskopan.type_adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dev.buskopan.model.Task;
import dev.buskopan.task_enum.TaskStatus;

import java.io.IOException;
import java.util.Arrays;

public class TaskType extends TypeAdapter<Task> {
    @Override
    public void write(JsonWriter out, Task task) throws IOException {
        out.beginObject();
        out.name("id").value(task.getId());
        out.name("name").value(task.getName());
        out.name("status").value(task.getStatus().getDisplay());
        out.endObject();
    }

    @Override
    public Task read(JsonReader in) throws IOException {
        in.beginObject();
        Task task = new Task();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "id" -> task.setId(in.nextLong());
                case "name" -> task.setName(in.nextString());
                case "status" -> task.setStatus(getStatus(in.nextString()));
            }
        }
        return task;
    }

    private TaskStatus getStatus(String statusString) {
        return Arrays.stream(TaskStatus.values())
                .filter(
                        e -> e.getDisplay().equals(statusString)
                )
                .findFirst()
                .orElse(TaskStatus.TODO);
    }
}
