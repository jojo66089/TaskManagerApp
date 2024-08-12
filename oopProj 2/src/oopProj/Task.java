package oopProj;

import java.util.Date;

public class Task {
    private String taskName;
    private Date taskDueDate;
    private Date taskDayMade;
    private String taskObjective;
    private boolean isCompleted;

    public Task(String taskName, Date taskDueDate, Date taskDayMade, String taskObjective) {
        this.taskName = taskName;
        this.taskDueDate = taskDueDate;
        this.taskDayMade = taskDayMade;
        this.taskObjective = taskObjective;
        this.isCompleted = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public Date getTaskDueDate() {
        return taskDueDate;
    }

    public Date getTaskDayMade() {
        return taskDayMade;
    }

    public String getTaskObjective() {
        return taskObjective;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }
}
