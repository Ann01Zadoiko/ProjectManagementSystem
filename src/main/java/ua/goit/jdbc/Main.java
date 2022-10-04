package ua.goit.jdbc;

import ua.goit.jdbc.command.*;
import ua.goit.jdbc.config.DataManagerConnector;

import ua.goit.jdbc.config.PropertiesConfig;
import ua.goit.jdbc.controller.Controller;
import ua.goit.jdbc.model.dao.DeveloperDao;
import ua.goit.jdbc.repository.*;
import ua.goit.jdbc.service.*;
import ua.goit.jdbc.service.converter.*;
import ua.goit.jdbc.view.Console;
import ua.goit.jdbc.view.View;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String dbUsername = System.getenv("dbUsername");
        String dbPassword = System.getenv("dbPassword");
        PropertiesConfig config = new PropertiesConfig();
        Properties properties = config.loadProperties("application.properties");
        DataManagerConnector connector = new DataManagerConnector(properties, dbUsername, dbPassword);
        Scanner scanner = new Scanner(System.in);
        View view = new Console(scanner);

        DeveloperRepository developerRepository = new DeveloperRepository(connector);
        ProjectRepository projectRepository = new ProjectRepository(connector);
        CompanyRepository companyRepository = new CompanyRepository(connector);
        CustomerRepository customerRepository = new CustomerRepository(connector);
        SkillRepository skillRepository = new SkillRepository(connector);

        DeveloperConverter developerConverter = new DeveloperConverter();
        ProjectConverter projectConverter = new ProjectConverter();
        CompanyConverter companyConverter = new CompanyConverter();
        CustomerConverter customerConverter = new CustomerConverter();
        SkillConverter skillConverter = new SkillConverter();

        DevelopersService developersService = new DevelopersService(developerRepository,developerConverter);
        ProjectsService projectsService = new ProjectsService(projectRepository,projectConverter);
        CompaniesService companiesService = new CompaniesService(companyRepository,companyConverter);
        CustomersService customersService = new CustomersService(customerRepository,customerConverter);
        SkillsService skillsService = new SkillsService(skillRepository,skillConverter);

        List<Command> commands = new ArrayList<>();
        commands.add(new CompanyCommand(view,companiesService));
        commands.add(new CustomerCommand(view,customersService));
        commands.add(new DeveloperCommand(view,developersService));
        commands.add(new Exit(view));
        commands.add(new Help(view));
        commands.add(new ListOfDevelopersForProject(view,developersService,projectsService));
        commands.add(new ListOfJavaDevelopers(view,developersService));
        commands.add(new ListOfMiddleDevelopers(view,developersService));
        commands.add(new ListOfProjects(view, projectsService));
        commands.add(new ProjectCommand(view,projectsService));
        commands.add(new SalaryForProject(view,projectsService,developersService));
        commands.add(new SkillCommand(view, skillsService));

        Controller controller = new Controller(view, commands);
        controller.run();
    }
}
