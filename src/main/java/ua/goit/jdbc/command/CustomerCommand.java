package ua.goit.jdbc.command;

import ua.goit.jdbc.model.dao.CustomerDao;
import ua.goit.jdbc.model.dto.CompanyDto;
import ua.goit.jdbc.model.dto.CustomerDto;
import ua.goit.jdbc.service.CustomersService;
import ua.goit.jdbc.view.View;

import java.util.List;

public class CustomerCommand implements Command{
    public static final String CUSTOMER_COMMEND = "customer";
    private final View view;
    private final CustomersService service;

    public CustomerCommand(View view, CustomersService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(CUSTOMER_COMMEND);
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
            view.write("Enter customer name");
            name = view.read();
            if (!service.isExist(name)) {
                break;
            }
            view.write("Customer already exists");
        }
        view.write("Enter customer location");
        String country = view.read();
        CustomerDto customerDto = new CustomerDto(name, country);
        service.save(customerDto);
        view.write(String.format("Customer %s added located in %s added to database", name, country));
    }

    public void read(){
        view.write("Enter customer name");
        String name = view.read();
        CustomerDto customerDto = service.findByName(name);
        view.write("Customer " + name + " located in " + customerDto.getCountry());
    }

    public void update(){
        String name;
        while (true) {
            view.write("Enter customer name");
            name = view.read();
            if (service.isExist(name)) {
                break;
            }
            view.write("Customer doesn't exist");
        }
        CustomerDto customerDto = service.findByName(name);
        view.write("Enter customer name");
        name = view.read();
        view.write("Enter customer location");
        String country = view.read();
        if (!name.equals("")) {
            customerDto.setName(name);
        }
        if (!country.equals("")) {
            customerDto.setCountry(country);
        }
        service.update(customerDto);
        view.write("Customer data updated");
    }

    public void delete(){
        String name;
        while (true) {
            view.write("Enter customer name");
            name = view.read();
            if (service.isExist(name)) {
                break;
            }
            view.write("Customer doesn't exist");
        }
        CustomerDto customerDto = service.findByName(name);
        service.delete(customerDto);
        if(!service.isExist(name)) {
            view.write(String.format("Company %s deleted from database", name));
        }
    }
}
