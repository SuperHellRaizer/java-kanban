package tasks;

import java.util.ArrayList;

public class Epic extends Task {
    final private ArrayList<Subtask> subtasks;

    public Epic(String name, String description) {
        super(name, description, TaskStatus.NEW);
        this.subtasks = new ArrayList<>();
    }

    public Epic(int id, String name, String description) {
        super(name, description, TaskStatus.NEW);
        this.subtasks = new ArrayList<>();
        this.id = id;
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", subtasksCount=" + subtasks.size() +
                '}';
    }
}