package oopProj;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Search {
    private List<Task> tasks;
    private List<Project> projects;

    public Search(List<Task> tasks, List<Project> projects) {
        this.tasks = tasks;
        this.projects = projects;
    }

    public List<Task> searchByName(String name) {
        List<Task> results = new ArrayList<>();
        results.addAll(tasks.stream().filter(task -> task.getTaskName().contains(name)).collect(Collectors.toList()));
        results.addAll(projects.stream().filter(project -> project.getTaskName().contains(name)).collect(Collectors.toList()));
        return results;
    }

    public List<Task> sortAlphabetically() {
        List<Task> allTasks = new ArrayList<>(tasks);
        allTasks.addAll(projects);
        allTasks.sort(Comparator.comparing(Task::getTaskName));
        return allTasks;
    }

    public List<Task> sortByDateCreated() {
        List<Task> allTasks = new ArrayList<>(tasks);
        allTasks.addAll(projects);
        allTasks.sort(Comparator.comparing(Task::getTaskDayMade));
        return allTasks;
    }

    public List<Task> sortByDueDate() {
        List<Task> allTasks = new ArrayList<>(tasks);
        allTasks.addAll(projects);
        allTasks.sort(Comparator.comparing(Task::getTaskDueDate));
        return allTasks;
    }
}
