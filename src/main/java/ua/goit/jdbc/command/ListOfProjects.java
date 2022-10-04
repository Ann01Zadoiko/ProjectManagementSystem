package ua.goit.jdbc.command;

import ua.goit.jdbc.service.ProjectsService;
import ua.goit.jdbc.view.View;

import java.util.List;

public class ListOfProjects implements Command{
    public static final String LIST_OF_PROJECTS = "query 5";
    private final View view;
    private final ProjectsService service;

    public ListOfProjects(View view, ProjectsService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(LIST_OF_PROJECTS);
    }

    @Override
    public void execute() {
        while (true) {
            try {
                List<String> list = service.getListOfProjects();
                System.out.println("List of projects: " + list);
                break;
            } catch (RuntimeException exception) {
                view.write(exception.getMessage());
            }
        }
    }
}
