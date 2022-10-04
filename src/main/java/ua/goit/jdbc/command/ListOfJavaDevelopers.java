package ua.goit.jdbc.command;

import ua.goit.jdbc.service.DevelopersService;
import ua.goit.jdbc.view.View;

import java.util.List;

public class ListOfJavaDevelopers implements Command{
    public static final String LIST_OF_JAVA_DEVELOPERS = "query 3";
    private final View view;
    private final DevelopersService service;

    public ListOfJavaDevelopers(View view, DevelopersService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(LIST_OF_JAVA_DEVELOPERS);
    }

    @Override
    public void execute() {
        view.write("Enter developer's language (Java, C++, JS): ");
        String language = view.read();
        while (true) {
            try {
                List<String> list = service.getListOfDevelopersByLanguage(language);
                System.out.println(list);
                break;
            } catch (RuntimeException exception) {
                view.write(exception.getMessage());
            }
        }
    }
}
