package tasks;

import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Subtask> subtasks;

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

    public void updateSubtask(Subtask subtask){
        subtasks.remove(subtask);
        subtasks.add(subtask);
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