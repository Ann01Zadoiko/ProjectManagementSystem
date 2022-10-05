package ua.goit.jdbc.command;

import ua.goit.jdbc.service.DevelopersService;
import ua.goit.jdbc.service.ProjectsService;
import ua.goit.jdbc.view.View;

public class SalaryForProject implements Command{
    public static final String SALARY_OF_ALL_DEVELOPER_OF_PROJECT = "query 1";
    private final View view;
    private final ProjectsService projectsService;
    private final DevelopersService developersService;

    public SalaryForProject(View view, ProjectsService projectsService, DevelopersService developersService) {
        this.view = view;
        this.projectsService = projectsService;
        this.developersService = developersService;
    }


    @Override
    public boolean canExecute(String input) {
        return input.equals(SALARY_OF_ALL_DEVELOPER_OF_PROJECT);
    }

    @Override
    public void execute() {
        projectsService.getListProject().stream().forEach(System.out::println);
        view.write("Enter project name");
        String projectName = view.read();
        while (true) {
            try {
                Integer salary = projectsService.getSalaryOfDevelopersFromProject(projectName);
                System.out.println("Salary of developers from " + projectName + ": " + salary);
                break;
            } catch (RuntimeException exception) {
                view.write(exception.getMessage());
            }
        }
    }
}
