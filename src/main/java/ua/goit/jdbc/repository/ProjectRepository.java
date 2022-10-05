package ua.goit.jdbc.repository;

import ua.goit.jdbc.config.DataManagerConnector;
import ua.goit.jdbc.model.dao.ProjectDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository implements Repository<ProjectDao>{
    private final DataManagerConnector connector;
    private static final String INSERT = "INSERT into projects (name_project, cost) values (?,?)";
    private static final String SELECT_BY_ID = "SELECT id_project, name_project, cost from projects where id_project = ?";
    private static final String UPDATE = "UPDATE projects set name_project = ?, cost = ? where id_project = ?";
    private static final String DELETE = "DELETE from projects where id_project = ?";
    private static final String SELECT_ALL = "SELECT id_project, name_project, cost from projects";
    private static final String SELECT_BY_NAME = "SELECT id_project, name_project, cost from projects where name_project = ?";

    private static final String SALARY_OF_DEVELOPERS = "SELECT sum(developers.salary)" +
            " FROM developers" +
            " JOIN developer_projects ON developer_projects.id_developer = developers.id_developer" +
            " JOIN projects ON projects.id_project = developer_projects.id_project" +
            " WHERE projects.name_project = ?;";
    private static final String LIST_OF_DEVELOPERS = "SELECT name_developer" +
            " FROM developers" +
            " JOIN developer_projects ON developer_projects.id_developer = developers.id_developer" +
            " JOIN projects ON projects.id_project = developer_projects.id_project" +
            " WHERE projects.name_project = ?";
    private static final String LIST_OF_PROJECTS =
            "SELECT projects.date_create, projects.name_project, COUNT(developers.name_developer) " +
                    " FROM projects" +
                    " JOIN developer_projects ON projects.id_project = developer_projects.id_project" +
                    " JOIN developers ON developers.id_developer = developer_projects.id_developer" +
                    " GROUP BY projects.date_create, projects.name_project" +
                    " ORDER BY name_project;";
    private static final String LIST_PROJECTS = "SELECT name_project FROM projects;";

    public ProjectRepository(DataManagerConnector connector) {
        this.connector = connector;
    }

    @Override
    public ProjectDao save(ProjectDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getCost());
            statement.setDate(3, Date.valueOf(entity.getDateOfCreation()));

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating project failed, no ID obtained.");
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Project is not created");
        }
        return entity;
    }

    @Override
    public ProjectDao update(ProjectDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setInt(3, entity.getCost());
            statement.setDate(4,Date.valueOf(entity.getDateOfCreation()));

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Project is not updated");
        }
        return entity;
    }

    @Override
    public ProjectDao findById(Integer id) {
        ProjectDao projectDao = null;
        try (Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        ResultSet resultSet = statement.executeQuery()){
            statement.setInt(1,id);
            while (resultSet.next()){
                projectDao = new ProjectDao();
                getEntity(resultSet, projectDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select project by id failed");
        }
        return projectDao;
    }

    @Override
    public List<ProjectDao> findAll() {
        List<ProjectDao> projectDaos = new ArrayList<>();
        try (Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                ProjectDao projectDao = new ProjectDao();
                getEntity(resultSet, projectDao);
                projectDaos.add(projectDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select all projects failed");
        }
        return projectDaos;
    }

    @Override
    public void delete(ProjectDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, entity.getId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Project is not deleted");
        }
    }


    public ProjectDao findByName(String name) {
        ProjectDao projectDao = null;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME);
             ResultSet resultSet = statement.executeQuery()){
            statement.setString(1,name);
            while (resultSet.next()){
                projectDao = new ProjectDao();
                getEntity(resultSet, projectDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select project by name failed");
        }
        return projectDao;
    }

    public Integer getSalaryOfDevelopersFromProject(String projectName) {
        int result = 0;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SALARY_OF_DEVELOPERS)) {
            statement.setString(1, projectName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Request failed!");
        }
        return result;
    }

    public List<String> getListOfDevelopers(String projectName) {
        List<String> list = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(LIST_OF_DEVELOPERS)) {
            statement.setString(1, projectName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("name_developer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Request failed!");
        }
        return list;
    }

    public List<String> getListOfProjects() {
        List<String> list = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(LIST_OF_PROJECTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                List<String> daoList = new ArrayList<>();
                daoList.add(resultSet.getString("date_create"));
                daoList.add(resultSet.getString("name_project"));
                daoList.add(resultSet.getString("count"));
                list.add(daoList.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Request failed!");
        }
        return list;
    }

    public List<String> getListProject(){
        List<String> list = new ArrayList<>();
        try (Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(LIST_PROJECTS);
        ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()){
                list.add(resultSet.getString("name_project"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Request failed!");
        }
        return list;
    }

    private void getEntity(ResultSet resultSet, ProjectDao projectDao) throws SQLException {
        projectDao.setId(resultSet.getInt("id_project"));
        projectDao.setName(resultSet.getString("name_project"));
        projectDao.setCost(resultSet.getInt("cost"));
        projectDao.setDateOfCreation(resultSet.getDate("date_create").toLocalDate());
    }
}
