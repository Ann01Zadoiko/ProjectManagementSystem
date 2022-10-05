package ua.goit.jdbc.command;

import ua.goit.jdbc.model.dao.DeveloperDao;
import ua.goit.jdbc.model.dto.CompanyDto;
import ua.goit.jdbc.model.dto.CustomerDto;
import ua.goit.jdbc.model.dto.DeveloperDto;
import ua.goit.jdbc.service.DevelopersService;
import ua.goit.jdbc.view.View;

import java.util.List;

public class DeveloperCommand implements Command{
    public static final String DEVELOPER_COMMAND = "developer";
    private final View view;
    private final DevelopersService service;

    public DeveloperCommand(View view, DevelopersService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DEVELOPER_COMMAND);
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
            view.write("Enter developer name");
            name = view.read();
            if (!service.isExist(name)) {
                break;
            }
            view.write("Developer already exists");
        }
        view.write("Enter developer age");
        Integer age = Integer.valueOf(view.read());
        view.write("Enter developer salary");
        Integer salary = Integer.valueOf(view.read());
        DeveloperDto developerDto = new DeveloperDto(name,age,salary);
        service.save(developerDto);
        view.write(String.format("Developer %s added, age %d added and salary %d added to database", name, age,salary));
    }

    public void read(){
        view.write("Enter developer name");
        String name = view.read();
        DeveloperDto developerDto = service.findByName(name);
        view.write("Developer " + name + ", age is " + developerDto.getAge() + " and salary is " + developerDto.getSalary());
    }

    public void update(){
        String name;
        while (true) {
            view.write("Enter developer name");
            name = view.read();
            if (service.isExist(name)) {
                break;
            }
            view.write("Developer doesn't exist");
        }
        DeveloperDto developerDto = service.findByName(name);
        view.write("Enter developer name");
        name = view.read();
        view.write("Enter developer age");
        Integer age = Integer.valueOf(view.read());
        view.write("Enter developer salary");
        Integer salary = Integer.valueOf(view.read());
        if (!name.equals("")) {
            developerDto.setName(name);
        }
        if (age != null){
            developerDto.setAge(age);
        }
        if (salary != null){
            developerDto.setSalary(salary);
        }
        service.update(developerDto);
        view.write("Developer data updated");
    }

    public void delete(){
        String name;
        while (true) {
            view.write("Enter developer name");
            name = view.read();
            if (service.isExist(name)) {
                break;
            }
            view.write("Developer doesn't exist");
        }
        DeveloperDto developerDto = service.findByName(name);
        service.delete(developerDto);
        if(!service.isExist(name)) {
            view.write(String.format("Developer %s deleted from database", name));
        }
    }
}
