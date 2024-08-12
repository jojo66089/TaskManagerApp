# Task and Project Management System

**Project Team**
- Luiz Simione (lvs2774) - Class Diagram, UI/UX Design and Core Method Implementation
- Nick Burwell  (nsb8225) - Use Case Diagrams, Testing/Method Contribution
- Joshua Guillen (jg6949) - Sequence Diagram, Testing/Method Contribution

## Overview
This project is a Task and Project Management System developed using Java's Swing framework. 
The application provides functionalities allowing users to manage tasks and projects efficiently. 
Key features include task and project management, deadline tracking, filtering options, and highlighting urgent tasks.

## Key Features
-  Project Management: Add, delete, and list projects or tasks. Every project must have at least one task, while tasks can exist independently.
- Viewing & Filtering: Filter projects and tasks by deadline (earliest to latest or latest to earliest), alphabetically, or by date created.
- Deadline and Progress Tracking: Monitor deadlines and update the progress of projects and tasks.
- Search Functionality: Find specific projects or tasks by name.
- Tagging Projects: Categorize projects and tasks for better organization.
- Highlighting Urgent Tasks: Automatically highlight tasks or projects that are due soon (within 48 hours).

## App Structure

- `Main.java`: The entry point of the application. This class launches the `TaskManagementApp`.
- `TaskManagementApp.java`: The main application class, responsible for handling user interactions, managing the UI, and coordinating between tasks and projects.
- `Task.java`: Represents an individual task, including attributes like name, due date, creation date, and status.
- `Project.java`: Represents a project that consists of multiple tasks. This class handles the addition and removal of tasks within a project.
- `Search.java`: Provides functionality to search for and sort tasks and projects based on different criteria.

## Requirements
- Java 17 or later: Ensure that you have JDK 17 or later installed on your machine.
- Swing Framework: The application uses Swing for the user interface, which is included with the JDK.

# Installation and Setup

1. Clone the Repository:
   ```bash
   git clone https://github.com/yourusername/task-management-app.git
   cd task-management-app
   ```

2. Compile the Project:
   Use `javac` to compile the project files:
   ```bash
   javac -d bin src/oopProj2/*.java
   ```

3. Run the Application:
   Launch the application using the `java` command:
   ```bash
   java -cp bin oopProj.Main
   ```

# Usage

Launching the Application
To start the application, run the `Main` class. This will open the main window of the task management system.

Adding Tasks
1. Click on "Add Task" to create a standalone task.
2. Fill in the details and submit the form to add the task to your list.

Adding Projects
1. Click on "Add Project" to create a new project.
2. Provide the necessary details, including at least one task associated with the project.

Viewing Tasks and Projects
- Use the "View Tasks" button to see a list of all tasks and projects.
- You can sort and filter tasks by various criteria using the options provided.

Searching
- Use the search bar to find specific tasks or projects by name.

Highlighting Urgent Tasks
- Tasks and projects that are due within 48 hours will be highlighted in the task list.

## Development Process

Design and Diagrams
- Use Cases: Defined and designed by Nick Burwell, focusing on how users interact with the system as well as the relationships that tasks will have with the management system as a whole .
- Sequence Diagram: Created by Joshua Guillen to illustrate the flow of operations within the Task Management Application and how the user will interact with the methods and conversely how the methods will interact with the user. 
- Class Diagrams: Developed by Luiz Simione to represent the structure and relationships between different classes in the system as well as define clear efficient methods that will be required by the Task Management application.

Coding
- UI/UX: Luiz Simione developed the user interface using `JFrame` and related Swing components.
- Core Functionality: Luiz Simione, Nick Burwell, and Josh Guillen implemented the application's methods and classes, focusing on ensuring the application works as intended.


# License

This project is licensed under the MIT License. See the `LICENSE` file for more details.


