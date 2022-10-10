package ua.goit.jdbc.command;

import ua.goit.jdbc.model.dao.CompanyDao;
import ua.goit.jdbc.model.dto.CompanyDto;
import ua.goit.jdbc.service.CompaniesService;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CompanyCommand implements Command{
    public static final String COMPANY_COMMAND = "company";
    private final View view;
    private final CompaniesService service;

    public CompanyCommand(View view, CompaniesService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(COMPANY_COMMAND);
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
            view.write("Enter company name");
            name = view.read();
            if (!service.isExist(name)) {
                break;
            }
            view.write("Company already exists");
        }
        view.write("Enter company location");
        String country = view.read();
        CompanyDto companyDto = new CompanyDto(name, country);
        service.save(companyDto);
        view.write(String.format("Company %s added located in %s added to database", name, country));
    }

    public void read(){
        view.write("Enter company name");
        String name = view.read();
        CompanyDto companyDto = service.findByName(name);
        view.write("Company " + name + " located in " + companyDto.getCountry());
    }

    public void update(){
        String name;
        while (true) {
            view.write("Enter company name");
            name = view.read();
            if (service.isExist(name)) {
                break;
            }
            view.write("Company doesn't exist");
        }
        CompanyDto companyDto = service.findByName(name);
        view.write("Enter new company name");
        name = view.read();
        view.write("Enter new company location");
        String country = view.read();
        if (!name.equals("")) {
            companyDto.setName(name);
        }
        if (!country.equals("")) {
            companyDto.setCountry(country);
        }
        service.update(companyDto);
        view.write("Company data updated");
    }

    public void delete(){
        String name;
        while (true) {
            view.write("Enter company name");
            name = view.read();
            if (service.isExist(name)) {
                break;
            }
            view.write("Company doesn't exist");
        }
        CompanyDto companyDto = service.findByName(name);
        service.delete(companyDto);
        if(!service.isExist(name)) {
            view.write(String.format("Company %s deleted from database", name));
        }
    }
}
