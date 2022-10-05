package ua.goit.jdbc.repository;

import ua.goit.jdbc.config.DataManagerConnector;
import ua.goit.jdbc.model.dao.DeveloperDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperRepository implements Repository<DeveloperDao> {
    private final DataManagerConnector connector;
    private static final String INSERT = "INSERT INTO developers (name_developer, age, salary) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE developers set name_developer = ?, age = ?, salary = ? where id_developer = ?";
    private static final String DELETE = "DELETE from developers where id_developer = ?";
    private static final String SELECT_BY_ID = "SELECT id_developer, name_developer, age, salary from developers where id_developer = ?";
    private static final String SELECT_ALL = "SELECT id_developer, name_developer, age, salary from developers";
    private static final String SELECT_BY_NAME = "SELECT id_developer, name_developer, age, salary from developers where name_developer = ?";

    private static final String LIST_OF_DEVELOPERS_BY_LANGUAGE =
            "SELECT developers.name_developer" +
                    "    FROM developers JOIN developer_skills ON developer_skills.id_developer = developers.id_developer" +
                    "    JOIN skills ON skills.id_skill = developer_skills.id_skill" +
                    "    where skills.programming_language = ?;";
    private static final String LIST_OF_DEVELOPERS_BY_SKILL_LEVEL =
            "SELECT developers.name_developer" +
                    " FROM developers JOIN developer_skills ON developer_skills.id_developer = developers.id_developer" +
                    " JOIN skills ON skills.id_skill = developer_skills.id_skill" +
                    " where skills.skill_level = ?;";

    public DeveloperRepository(DataManagerConnector connector) {
        this.connector = connector;
    }

    @Override
    public DeveloperDao save(DeveloperDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getAge());
            statement.setInt(3, entity.getSalary());

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating developer failed, no ID obtained.");
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Developer is not created");
        }
        return entity;
    }

    @Override
    public DeveloperDao update(DeveloperDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getAge());
            statement.setInt(3, entity.getSalary());
            statement.setInt(4, entity.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Developer is not updated");
        }
        return entity;
    }

    @Override
    public DeveloperDao findById(Integer id) {
        DeveloperDao developerDao = null;
        try (Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        ResultSet resultSet = statement.executeQuery()){
            statement.setInt(1,id);
            while (resultSet.next()){
                developerDao = new DeveloperDao();
                getEntity(resultSet, developerDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select developer by id failed");
        }
        return developerDao;
    }

    @Override
    public List<DeveloperDao> findAll() {
        List<DeveloperDao> developerDaos = new ArrayList<>();
        try (Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                DeveloperDao developerDao = new DeveloperDao();
                getEntity(resultSet, developerDao);
                developerDaos.add(developerDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select all developers failed");
        }
        return developerDaos;
    }

    @Override
    public void delete(DeveloperDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, entity.getId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Developer is not deleted");
        }
    }


    public DeveloperDao findByName(String name) {
        DeveloperDao developerDao = null;
        try (Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME);
        ResultSet resultSet = statement.executeQuery()){
            statement.setString(1, name);
            while (resultSet.next()){
                developerDao = new DeveloperDao();
                getEntity(resultSet, developerDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select developer by name failed");
        }
        return developerDao;
    }

    public List<String> getListOfDevelopersByLanguage(String language){
        List<String> list = new ArrayList<>();
        ResultSet resultSet;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(LIST_OF_DEVELOPERS_BY_LANGUAGE)) {
            statement.setString(1, language);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("name_developer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Request failed!");
        }
        return list;
    }

    public List<String> getListOfDevelopersBySkillLevel(String skillLevel){
        List<String> list = new ArrayList<>();
        ResultSet resultSet;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(LIST_OF_DEVELOPERS_BY_SKILL_LEVEL)) {
            statement.setString(1, skillLevel);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("name_developer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Request failed!");
        }
        return list;
    }

    private void getEntity(ResultSet resultSet, DeveloperDao developerDao) throws SQLException {
        developerDao.setId(resultSet.getInt("id_developer"));
        developerDao.setName(resultSet.getString("name_developer"));
        developerDao.setAge(resultSet.getInt("age"));
        developerDao.setSalary(resultSet.getInt("salary"));
    }
}
