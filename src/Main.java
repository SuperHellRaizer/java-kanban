/* Сергей, здравствуйте! Большое спасибо за Ваши комментарии! К сожалению, я не совсем понял, где именно оставлять
* сообщение для Вас, оставил его здесь. Если так не надо - прошу прощения! Расписал логику работы метода
* updateEpicStatus в комментариях. Если это тоже неверно, то тогда переделаю метод как-то по другому. Добавил
* несколько тестов в Main по скорректированным методам. */

import tasks.*;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // Создание двух задач:
        Task task1 = taskManager.createTask(new Task("Задача №1", "Описание задачи №1",
                TaskStatus.NEW));
        Task task2 = taskManager.createTask(new Task("Задача №2", "Описание задачи №2",
                TaskStatus.NEW));

        // Создание Эпика с двумя подзачами:
        Epic epic1 = taskManager.createEpic(new Epic("AEZAKMI", "HESOYAM"));
        Subtask subtask1 = taskManager.createSubtask(new Subtask("Подзадача №1", "Описание подзадачи №1",
                TaskStatus.NEW, epic1.getId()));
        Subtask subtask2 = taskManager.createSubtask(new Subtask("Подзадача №2", "Описание подзадачи №2",
                TaskStatus.NEW, epic1.getId()));

        // Создание Эпика с одной подзадачей:
        Epic epic2 = taskManager.createEpic(new Epic("Эпик №2", "Описание эпика №2"));
        Subtask subtask3 = taskManager.createSubtask(new Subtask("Подзадача №3", "Описание подзадачи №3",
                TaskStatus.NEW, epic2.getId()));

        // Вывод всех списков:
        System.out.println("Все задачи:");
        System.out.println(taskManager.getAllTasks());
        System.out.println("\nВсе эпики:");
        System.out.println(taskManager.getAllEpics());
        System.out.println("\nВсе подзадачи:");
        System.out.println(taskManager.getAllSubtasks());

        //Обновление(корректировка) эпика №1
        System.out.println("\nОбновление(корректировка) эпика №1.");
        Epic updatedEpic1 = new Epic(epic1.getId(), "Эпик №1", "Описание эпика №1");
        taskManager.updateEpic(updatedEpic1);

        //Вывод исправленного эпика №1
        System.out.println("\nВывод исправленного эпика №1:");
        System.out.println(taskManager.getEpicById(epic1.getId()));

        //Добавление подзадачи в эпик №1
        System.out.println("\nДобавление подзадачи в эпик №1:");
        Subtask subtask4 = taskManager.createSubtask(new Subtask("WANRLTV", "YECGAA",
                TaskStatus.NEW, epic1.getId()));

        //Вывод всех подзадач у эпика №1
        System.out.println("\nВывод всех подзадач у эпика №1:");
        System.out.println(taskManager.getAllSubtasksByEpicId(epic1));

        //Обновление подзадачи
        System.out.println("\nОбновление подзадачи:");
        Subtask updatedSubtask4 = new Subtask(subtask4.getId(), "Подзадача №4", "Описание подзадачи №4",
                TaskStatus.NEW, epic1.getId());
        taskManager.updateSubtask(updatedSubtask4);

        //Вывод всех подзадач у эпика №1
        System.out.println("\nВывод всех подзадач у эпика №1:");
        System.out.println(taskManager.getAllSubtasksByEpicId(epic1));

        //Удаляем добавленную подзадачу
        System.out.println("\nУдаляем добавленную подзадачу:");
        taskManager.deleteSubtask(subtask4.getId());

        //Вывод всех подзадач у эпика №1
        System.out.println("\nВывод всех подзадач у эпика №1:");
        System.out.println(taskManager.getAllSubtasksByEpicId(epic1));

        // Вывод по одной задаче/эпику/подзадачи по идентификатору:
        System.out.println("\nВывести одну задачу:");
        System.out.println(taskManager.getTaskById(1));
        System.out.println("Вывести один эпик:");
        System.out.println(taskManager.getEpicById(3));
        System.out.println("Вывести одну подзадачу:");
        System.out.println(taskManager.getSubtaskById(4));

        // Изменение статусов:
        task1.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateTask(task1);
        task2.setStatus(TaskStatus.DONE);
        taskManager.updateTask(task2);

        subtask1.setStatus(TaskStatus.NEW);
        taskManager.updateSubtask(subtask1);
        subtask2.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateSubtask(subtask2);
        subtask3.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(subtask3);

        // Вывод(печать) обновленных списков:
        System.out.println("\nПосле изменения статусов:");
        System.out.println("Все задачи:");
        System.out.println(taskManager.getAllTasks());
        System.out.println("\nВсе эпики:");
        System.out.println(taskManager.getAllEpics());
        System.out.println("\nВсе подзадачи:");
        System.out.println(taskManager.getAllSubtasks());

        // Удаление одной задачи и одного эпика:
        taskManager.deleteTask(task2.getId());
        taskManager.deleteEpic(epic2.getId());

        // Вывод(печать) списков после выборочного удаления:
        System.out.println("\nПосле удаления:");
        System.out.println("Все задачи:");
        System.out.println(taskManager.getAllTasks());
        System.out.println("\nВсе эпики:");
        System.out.println(taskManager.getAllEpics());
        System.out.println("\nВсе подзадачи:");
        System.out.println(taskManager.getAllSubtasks());

        // Удаление всех задач:
        taskManager.deleteAllTasks();
        taskManager.deleteAllEpics();
        taskManager.deleteAllSubtasks();

        // Вывод(печать) спмсков после полной очистки
        System.out.println("\nСписки очищены:");
        System.out.println("Все задачи:");
        System.out.println(taskManager.getAllTasks());
        System.out.println("\nВсе эпики:");
        System.out.println(taskManager.getAllEpics());
        System.out.println("\nВсе подзадачи:");
        System.out.println(taskManager.getAllSubtasks());
    }
}