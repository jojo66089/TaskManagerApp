package oopProj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project extends Task {
    private List<Task> tasks;
    private boolean isCompleted;

    public Project(String projectName, Date taskDueDate, Date taskDayMade, String taskObjective, int numberOfTasks) {
        super(projectName, taskDueDate, taskDayMade, taskObjective);
        this.tasks = new ArrayList<>(numberOfTasks);
        this.isCompleted = false;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public void markTaskAsCompleted(Task task) {
        task.markAsCompleted();
        checkIfProjectIsCompleted();
    }

    public void checkIfProjectIsCompleted() {
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                return;
            }
        }
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
