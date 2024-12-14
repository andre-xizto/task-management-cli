package dev.buskopan.task_enum;

public enum TaskStatus {
    TODO("To do"),
    IN_PROGRESS("In progress"),
    COMPLETE("Completed");

    private final String display;

    TaskStatus(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
