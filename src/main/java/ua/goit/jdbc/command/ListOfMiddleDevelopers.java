package ua.goit.jdbc.command;

import ua.goit.jdbc.service.DevelopersService;
import ua.goit.jdbc.service.SkillsService;
import ua.goit.jdbc.view.View;

import java.util.List;

public class ListOfMiddleDevelopers implements Command{
    public static final String LIST_OF_MIDDLE_DEVELOPERS = "query 4";
    private final View view;
    private final DevelopersService developersService;
    private final SkillsService skillsService;

    public ListOfMiddleDevelopers(View view, DevelopersService developersService, SkillsService skillsService) {
        this.view = view;
        this.developersService = developersService;
        this.skillsService = skillsService;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(LIST_OF_MIDDLE_DEVELOPERS);
    }

    @Override
    public void execute() {
        skillsService.getListOfLevel().stream().forEach(System.out::println);
        view.write("Enter developer's skill level");
        String skillLevel = view.read();
        while (true) {
            try {
                List<String> list = developersService.getListOfDevelopersBySkillLevel(skillLevel);
                System.out.println(list);
                break;
            } catch (RuntimeException exception) {
                view.write(exception.getMessage());
            }
        }
    }
}

