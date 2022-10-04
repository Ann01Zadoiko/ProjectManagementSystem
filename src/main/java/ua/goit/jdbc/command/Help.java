package ua.goit.jdbc.command;

import ua.goit.jdbc.view.View;

public class Help implements Command{
    private static final String HELP = "help";
    private final View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(HELP);
    }

    @Override
    public void execute() {
        view.write(String.format("Enter %s to see all command", Help.HELP));
        view.write(String.format("Enter %s to exit program",Exit.EXIT));
        view.write(String.format("Enter %s command to get a salary (sum) of all developers of a project", SalaryForProject.SALARY_OF_ALL_DEVELOPER_OF_PROJECT));
        view.write(String.format("Enter %s command to get a list of Java developers", ListOfJavaDevelopers.LIST_OF_JAVA_DEVELOPERS));
        view.write(String.format("Enter %s command to get a list of developers from project", ListOfDevelopersForProject.LIST_OF_DEVELOPERS_OF_PROJECT));
        view.write(String.format("Enter %s command to get a list of Middle developers", ListOfMiddleDevelopers.LIST_OF_MIDDLE_DEVELOPERS));
        view.write(String.format("Enter %s command to get a list of projects", ListOfProjects.LIST_OF_PROJECTS));
        view.write(String.format("Enter %s and choose one of four commands",CompanyCommand.COMPANY_COMMAND));
        view.write(String.format("Enter %s and choose one of four commands", CustomerCommand.CUSTOMER_COMMEND));
        view.write(String.format("Enter %s and choose one of four commands", DeveloperCommand.DEVELOPER_COMMAND));
        view.write(String.format("Enter %s and choose one of four commands", ProjectCommand.PROJECT_COMMAND));
        view.write(String.format("Enter %s and choose one of four commands", SkillCommand.SKILL_COMMAND));
    }
}
