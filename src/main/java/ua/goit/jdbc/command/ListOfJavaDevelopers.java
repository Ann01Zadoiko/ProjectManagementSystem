package ua.goit.jdbc.command;

import ua.goit.jdbc.service.DevelopersService;
import ua.goit.jdbc.service.SkillsService;
import ua.goit.jdbc.view.View;

import java.util.List;

public class ListOfJavaDevelopers implements Command{
    public static final String LIST_OF_JAVA_DEVELOPERS = "query 3";
    private final View view;
    private final DevelopersService developersService;
    private final SkillsService skillsService;

    public ListOfJavaDevelopers(View view, DevelopersService developersService, SkillsService skillsService) {
        this.developersService = developersService;
        this.view = view;
        this.skillsService = skillsService;
    }


    @Override
    public boolean canExecute(String input) {
        return input.equals(LIST_OF_JAVA_DEVELOPERS);
    }

    @Override
    public void execute() {
        skillsService.getListOfLanguage().stream().forEach(System.out::println);
        view.write("Enter developer's language: ");
        String language = view.read();
        while (true) {
            try {
                List<String> list = developersService.getListOfDevelopersByLanguage(language);
                System.out.println(list);
                break;
            } catch (RuntimeException exception) {
                view.write(exception.getMessage());
            }
        }
    }
}
