package oopProj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskManagementApp extends JFrame {
    private List<Task> tasks = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> taskList = new JList<>(listModel);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TaskManagementApp() {
        setTitle("Task Management Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Task Management Application");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton viewTasksButton = new JButton("View Tasks");
        JButton addTaskButton = new JButton("Add Task");
        JButton addProjectButton = new JButton("Add Project");

        viewTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTasks();
            }
        });

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        addProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProject();
            }
        });

        buttonPanel.add(viewTasksButton);
        buttonPanel.add(addTaskButton);
        buttonPanel.add(addProjectButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void viewTasks() {
        JFrame viewTasksFrame = new JFrame("View Tasks");
        viewTasksFrame.setSize(600, 400);

        DefaultListModel<String> viewListModel = new DefaultListModel<>();
        JList<String> viewTaskList = new JList<>(viewListModel);
        for (Task task : tasks) {
            viewListModel.addElement(task.getTaskName() + " - " + task.getTaskObjective() + " (Due: " + dateFormat.format(task.getTaskDueDate()) + ")");
        }
        for (Project project : projects) {
            viewListModel.addElement("Project: " + project.getTaskName() + " (Due: " + dateFormat.format(project.getTaskDueDate()) + ")");
            for (Task task : project.getTasks()) {
                viewListModel.addElement("  - " + task.getTaskName() + " - " + task.getTaskObjective() + " (Due: " + dateFormat.format(task.getTaskDueDate()) + ")");
            }
        }

        // Search and Sort Panel
        JPanel searchSortPanel = new JPanel();
        searchSortPanel.setLayout(new FlowLayout());

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton sortAlphabeticallyButton = new JButton("Sort Alphabetically");
        JButton sortByDateCreatedButton = new JButton("Sort by Date Created");
        JButton sortByDueDateButton = new JButton("Sort by Due Date");

        searchSortPanel.add(new JLabel("Search:"));
        searchSortPanel.add(searchField);
        searchSortPanel.add(searchButton);
        searchSortPanel.add(sortAlphabeticallyButton);
        searchSortPanel.add(sortByDateCreatedButton);
        searchSortPanel.add(sortByDueDateButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                Search search = new Search(tasks, projects);
                List<Task> results = search.searchByName(query);
                updateViewTaskList(viewListModel, results);
            }
        });

        sortAlphabeticallyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search search = new Search(tasks, projects);
                List<Task> sortedTasks = search.sortAlphabetically();
                updateViewTaskList(viewListModel, sortedTasks);
            }
        });

        sortByDateCreatedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search search = new Search(tasks, projects);
                List<Task> sortedTasks = search.sortByDateCreated();
                updateViewTaskList(viewListModel, sortedTasks);
            }
        });

        sortByDueDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search search = new Search(tasks, projects);
                List<Task> sortedTasks = search.sortByDueDate();
                updateViewTaskList(viewListModel, sortedTasks);
            }
        });

        viewTasksFrame.add(searchSortPanel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(viewTaskList);
        viewTasksFrame.add(scrollPane, BorderLayout.CENTER);

        viewTasksFrame.setVisible(true);
    }

    private void addTask() {
        JFrame addTaskFrame = new JFrame("Add Task");
        addTaskFrame.setSize(400, 300);
        addTaskFrame.setLayout(new GridLayout(5, 2, 10, 10));

        JTextField taskNameField = new JTextField();
        JTextField taskDueDateField = new JTextField();
        JTextField taskObjectiveField = new JTextField();
        JButton saveTaskButton = new JButton("Save Task");

        addTaskFrame.add(new JLabel("Task Name:"));
        addTaskFrame.add(taskNameField);
        addTaskFrame.add(new JLabel("Task Due Date (yyyy-MM-dd):"));
        addTaskFrame.add(taskDueDateField);
        addTaskFrame.add(new JLabel("Task Objective:"));
        addTaskFrame.add(taskObjectiveField);
        addTaskFrame.add(new JLabel());
        addTaskFrame.add(saveTaskButton);

        saveTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = taskNameField.getText();
                String taskDueDate = taskDueDateField.getText();
                String taskObjective = taskObjectiveField.getText();
                try {
                    Date dueDate = dateFormat.parse(taskDueDate);
                    Date dayMade = new Date();
                    Task task = new Task(taskName, dueDate, dayMade, taskObjective);
                    tasks.add(task);
                    listModel.addElement(task.getTaskName());
                    addTaskFrame.dispose();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy-MM-dd.");
                }
            }
        });

        addTaskFrame.setVisible(true);
    }

    private void addProject() {
        JFrame addProjectFrame = new JFrame("Add Project");
        addProjectFrame.setSize(400, 300);
        addProjectFrame.setLayout(new GridLayout(6, 2, 10, 10));

        JTextField projectNameField = new JTextField();
        JTextField projectDueDateField = new JTextField();
        JTextField projectObjectiveField = new JTextField();
        JTextField numberOfTasksField = new JTextField();
        JButton saveProjectButton = new JButton("Save Project");

        addProjectFrame.add(new JLabel("Project Name:"));
        addProjectFrame.add(projectNameField);
        addProjectFrame.add(new JLabel("Project Due Date (yyyy-MM-dd):"));
        addProjectFrame.add(projectDueDateField);
        addProjectFrame.add(new JLabel("Project Objective:"));
        addProjectFrame.add(projectObjectiveField);
        addProjectFrame.add(new JLabel("Number of Tasks:"));
        addProjectFrame.add(numberOfTasksField);
        addProjectFrame.add(new JLabel());
        addProjectFrame.add(saveProjectButton);

        saveProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String projectName = projectNameField.getText();
                String projectDueDate = projectDueDateField.getText();
                String projectObjective = projectObjectiveField.getText();
                int numberOfTasks = Integer.parseInt(numberOfTasksField.getText());
                try {
                    Date dueDate = dateFormat.parse(projectDueDate);
                    Date dayMade = new Date();
                    Project project = new Project(projectName, dueDate, dayMade, projectObjective, numberOfTasks);

                    // Adding specified number of tasks to the project
                    for (int i = 0; i < numberOfTasks; i++) {
                        JTextField taskNameField = new JTextField();
                        JTextField taskDueDateField = new JTextField();
                        JTextField taskObjectiveField = new JTextField();
                        JOptionPane.showMessageDialog(null, taskNameField, "Enter Task Name " + (i + 1), JOptionPane.PLAIN_MESSAGE);
                        JOptionPane.showMessageDialog(null, taskDueDateField, "Enter Task Due Date " + (i + 1), JOptionPane.PLAIN_MESSAGE);
                        JOptionPane.showMessageDialog(null, taskObjectiveField, "Enter Task Objective " + (i + 1), JOptionPane.PLAIN_MESSAGE);
                        Date taskDueDate = dateFormat.parse(taskDueDateField.getText());
                        Task task = new Task(taskNameField.getText(), taskDueDate, dayMade, taskObjectiveField.getText());
                        project.addTask(task);
                    }

                    projects.add(project);
                    listModel.addElement(project.getTaskName());
                    addProjectFrame.dispose();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy-MM-dd.");
                }
            }
        });

        addProjectFrame.setVisible(true);
    }

    private void updateViewTaskList(DefaultListModel<String> viewListModel, List<Task> tasks) {
        viewListModel.clear();
        for (Task task : tasks) {
            viewListModel.addElement(task.getTaskName() + " - " + task.getTaskObjective() + " (Due: " + dateFormat.format(task.getTaskDueDate()) + ")");
        }
        for (Project project : projects) {
            viewListModel.addElement("Project: " + project.getTaskName() + " (Due: " + dateFormat.format(project.getTaskDueDate()) + ")");
            for (Task task : project.getTasks()) {
                viewListModel.addElement("  - " + task.getTaskName() + " - " + task.getTaskObjective() + " (Due: " + dateFormat.format(task.getTaskDueDate()) + ")");
            }
        }
    }

  
}

                       
