import tasks.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    final private HashMap<Integer, Task> tasks = new HashMap<>();
    final private HashMap<Integer, Epic> epics = new HashMap<>();
    final private HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private int generatorId = 1;

    private int getNextId() {
        return generatorId++;
    }

    // Методы для обычных задач
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Task createTask(Task task) {
        task.setId(getNextId());
        tasks.put(task.getId(), task);
        return task;
    }

    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    // Методы для эпиков
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public Epic createEpic(Epic epic) {
        epic.setId(getNextId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public void updateEpic(Epic epic) {                 // метод обновления эпика
        if (epics.containsKey(epic.getId())) {
            Epic savedEpic = epics.get(epic.getId());
            savedEpic.setName(epic.getName());
            savedEpic.setDescription(epic.getDescription());
            updateEpicStatus(savedEpic.getId());
        }
    }

    private void updateEpicStatus(int id) {
        Epic epic = epics.get(id);
        if (epic == null) return;

        ArrayList<Subtask> epicSubtasks = epic.getSubtasks();

        if (epicSubtasks.isEmpty()) {    // если пусто - статус NEW
            epic.setStatus(TaskStatus.NEW);
            return;
        }

        for (Subtask subtask : epicSubtasks) {  // сначала проверяем, если есть хоть один IN_PROGRESS
                                                // - значит эпик в состоянии IN_PROGRESS
            if (subtask.getStatus() == TaskStatus.IN_PROGRESS) {
                epic.setStatus(TaskStatus.IN_PROGRESS);
                return;
            }
        }

        for (Subtask subtask : epicSubtasks) { // если не нашли IN_PROGRESS,остается два состояния эпика.Сначалаищем NEW
                                               // если есть хоть один NEW, значит эпик тоже NEW
            if (subtask.getStatus() == TaskStatus.NEW || epicSubtasks.isEmpty()) {
                epic.setStatus(TaskStatus.NEW);
                return;
            }
        }
        epic.setStatus(TaskStatus.DONE); // если не нашли ни IN_PROGRESS, ни NEW, значит все сабтаски DONE, потому что
    }                                    // каких-либо других состояний у них быть не может

    public void deleteEpic(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            for (Subtask subtask : epic.getSubtasks()) {
                subtasks.remove(subtask.getId());
            }
        }
    }

    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    // Методы для подзадач
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public Subtask createSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            return null;
        }
        subtask.setId(getNextId());
        subtasks.put(subtask.getId(), subtask);
        epic.getSubtasks().add(subtask);
        updateEpicStatus(epic.getId());
        return subtask;
    }

    public void updateSubtask(Subtask updSubtask) {
        Epic epic = epics.get(updSubtask.getEpicId());
        if (epic == null) {
            return;
        }

        if (subtasks.containsKey(updSubtask.getId())) {
            subtasks.put(updSubtask.getId(), updSubtask);

            ArrayList<Subtask> epicSubtasks = epic.getSubtasks();

            for (int i = 0; i < epicSubtasks.size(); i++) {
                if (epicSubtasks.get(i).getId() == updSubtask.getId()) {
                    epicSubtasks.set(i, updSubtask); // Заменяем по индексу
                    updateEpicStatus(updSubtask.getEpicId());  // обновили эпик
                    break;
                }
            }
        }
    }

    public void deleteSubtask(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.getSubtasks().remove(subtask);
                updateEpicStatus(epic.getId());
            }
        }
    }

    public void deleteAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasks().clear();
            updateEpicStatus(epic.getId());
        }
        subtasks.clear();
    }

    // Дополнительные методы
    public ArrayList<Subtask> getAllSubtasksByEpicId(Epic epic) { // Получение списка всех подзадач определенного эпика
        Epic neededEpic = epics.get(epic.getId());
        if (neededEpic != null) {
            return new ArrayList<>(neededEpic.getSubtasks());
        }
        return new ArrayList<>();
    }
}