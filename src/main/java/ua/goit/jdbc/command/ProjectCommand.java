package ua.goit.jdbc.command;

import ua.goit.jdbc.model.dao.ProjectDao;
import ua.goit.jdbc.model.dto.DeveloperDto;
import ua.goit.jdbc.model.dto.ProjectDto;
import ua.goit.jdbc.service.ProjectsService;
import ua.goit.jdbc.view.View;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
            case "create": update();
            case "read": read();
            case "update": update();
            case "delete": delete();
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
        view.write("Enter project cost:");
        Integer cost = Integer.valueOf(view.read());
        view.write("Enter data create");
        LocalDate dataCreate = LocalDate.parse(view.read());
        ProjectDto projectDto = new ProjectDto(name,cost,dataCreate);
        service.save(projectDto);
        view.write(String.format("Project %s added, cost %d added and data create %d added to database", name, cost,dataCreate));
    }

    public void read(){
        view.write("Enter project name");
        String name = view.read();
        ProjectDto projectDto = service.findByName(name);
        view.write("Project name " + projectDto.getName() + ", cost " + projectDto.getCost() + " and date created " + projectDto.getDateOfCreation());
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
        view.write("Enter project name");
        name = view.read();
        view.write("Enter project cost");
        Integer cost = Integer.valueOf(view.read());
        view.write("Enter date created");
        LocalDate dateCreate = LocalDate.parse(view.read());
        if (!name.equals("")){
            projectDto.setName(name);
        }
        if (cost != null){
            projectDto.setCost(cost);
        }
        if (!dateCreate.equals(null)){
            projectDto.setDateOfCreation(dateCreate);
        }
        service.update(projectDto);
        view.write("Project data updated");
    }

    public void delete(){
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
        service.delete(projectDto);
        if(!service.isExist(name)) {
            view.write(String.format("Project %s deleted from database", name));
        }
    }
}
