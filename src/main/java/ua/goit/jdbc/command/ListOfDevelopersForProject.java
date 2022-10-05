package ua.goit.jdbc.command;

import ua.goit.jdbc.service.DevelopersService;
import ua.goit.jdbc.service.ProjectsService;
import ua.goit.jdbc.view.View;

import java.util.List;

public class ListOfDevelopersForProject implements Command{
    public static final String LIST_OF_DEVELOPERS_OF_PROJECT = "query 2";
    private final View view;
    private final DevelopersService developersService;
    private final ProjectsService projectsService;

    public ListOfDevelopersForProject(View view, DevelopersService developersService, ProjectsService projectsService) {
        this.view = view;
        this.developersService = developersService;
        this.projectsService = projectsService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(LIST_OF_DEVELOPERS_OF_PROJECT);
    }

    @Override
    public void execute() {
        projectsService.getListProject().stream().forEach(System.out::println);
        view.write("Enter project name");
        String projectName = view.read();
        while (true) {
            try {
                List<String> projectDevelopers = projectsService.getListOfDevelopers(projectName);
                System.out.println("Developers name from " + projectName + " " + projectDevelopers);
                break;
            } catch (RuntimeException exception) {
                view.write(exception.getMessage());
            }
        }
    }
}
