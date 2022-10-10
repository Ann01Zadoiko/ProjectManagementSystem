package ua.goit.jdbc.command;

import ua.goit.jdbc.model.dao.ProjectDao;
import ua.goit.jdbc.model.dto.DeveloperDto;
import ua.goit.jdbc.model.dto.ProjectDto;
import ua.goit.jdbc.service.ProjectsService;
import ua.goit.jdbc.view.View;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ProjectCommand implements Command{
    public static final String PROJECT_COMMAND = "project";
    private final View view;
    private final ProjectsService service;

    public ProjectCommand(View view, ProjectsService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(PROJECT_COMMAND);
    }

    @Override
    public void execute() {
        view.write("Enter next command (create, read, update or delete):");
        String command = view.read();
        switch (command){
            case "create": create(); break;
            case "read": read(); break;
            case "update": update(); break;
            case "delete": delete(); break;
        }
    }

    public void create(){
        String name;
        while (true) {
            view.write("Enter project name");
            name = view.read();
            if (!service.isExist(name)) {
                break;
            }
            view.write("Project already exists");
        }
        view.write("Enter project id");
        Integer id = Integer.valueOf(view.read());
        view.write("Enter project cost:");
        Integer cost = Integer.valueOf(view.read());
        view.write("Enter data create");
        LocalDateTime dataCreate = LocalDateTime.parse(view.read());
        ProjectDto projectDto = new ProjectDto(id, name,cost,dataCreate);
        service.save(projectDto);
        view.write(String.format("Project %s added, cost %d added and data create %d added to database", name, cost,dataCreate));
    }

    public void read(){
        view.write("Enter project name");
        String name = view.read();
        ProjectDto projectDto = service.findByName(name);
        view.write("Project name " + projectDto.getName() + ", cost " + projectDto.getCost() + " and date created " + projectDto.getDateCreate());
    }

    public void update(){
        String name;
        while (true) {
            view.write("Enter project name");
            name = view.read();
            if (service.isExist(name)) {
                break;
            }
            view.write("Project doesn't exist");
        }
        ProjectDto projectDto = service.findByName(name);
        view.write("Enter new project name");
        name = view.read();
        view.write("Enter new project cost");
        Integer cost = Integer.valueOf(view.read());
        view.write("Enter new date created");
        LocalDateTime dateCreate = LocalDateTime.parse(view.read());
        if (!name.equals("")){
            projectDto.setName(name);
        }
        if (cost != null){
            projectDto.setCost(cost);
        }
        if (!dateCreate.equals(null)){
            projectDto.setDateCreate(dateCreate);
        }
        service.update(projectDto);
        view.write("Project data updated");
    }

    public void delete(){
        view.write("Enter project id");
        int id = Integer.parseInt(view.read());
        while (true) {
            try {
                ProjectDto project = service.getById(id);
                service.delete(project);
            } catch (RuntimeException exception) {
                view.write(exception.getMessage());
            }
        }
    }
}
