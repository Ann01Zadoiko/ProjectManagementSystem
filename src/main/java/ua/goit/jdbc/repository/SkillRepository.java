package ua.goit.jdbc.repository;

import ua.goit.jdbc.config.DataManagerConnector;
import ua.goit.jdbc.model.dao.SkillDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SkillRepository implements Repository<SkillDao>{
    private final DataManagerConnector connector;
    private static final String INSERT = "INSERT into skills (programming_language, skill_level) values (?,?)";
    private static final String SELECT_BY_ID = "SELECT id_skill, programming_language, skill_level from skills where id_skill = ?";
    private static final String UPDATE = "UPDATE skills set programming_language = ?, skill_level = ? where id_skill = ?";
    private static final String DELETE = "DELETE from skills where id_skill = ?";
    private static final String SELECT_ALL = "SELECT id_skill, programming_language, skill_level from skills";
    private static final String SELECT_BY_LANGUAGE_AND_LEVEL =
            "SELECT id_skill, programming_language, skill_level from skills where programming_language = ?, skill_level = ?";
    private static final String LIST_OF_LANGUAGE = "SELECT programming_language from skills;";
    private static final String LIST_OF_LEVEL = "SELECT skill_level from skills;";

    public SkillRepository(DataManagerConnector connector) {
        this.connector = connector;
    }

    @Override
    public SkillDao save(SkillDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getProgrammingLanguage());
            statement.setString(2, entity.getSkillLevel());

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating skill failed, no ID obtained.");
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Skill is not created");
        }
        return entity;
    }

    @Override
    public SkillDao update(SkillDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, entity.getProgrammingLanguage());
            statement.setString(2, entity.getSkillLevel());
            statement.setInt(3, entity.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Skill is not updated");
        }
        return entity;
    }

    @Override
    public SkillDao findById(Integer id) {
        SkillDao skillDao = null;
        try (Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        ResultSet resultSet = statement.executeQuery()){
            statement.setInt(1,id);
            while (resultSet.next()){
                skillDao = new SkillDao();
                getEntity(resultSet, skillDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select skill by id failed");
        }
        return skillDao;
    }

    @Override
    public List<SkillDao> findAll() {
        List<SkillDao> skillDaos = new ArrayList<>();
        try (Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                SkillDao skillDao = new SkillDao();
                getEntity(resultSet, skillDao);
                skillDaos.add(skillDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select all skills failed");
        }
        return skillDaos;
    }

    @Override
    public void delete(SkillDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, entity.getId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Skill is not deleted");
        }
    }


    public SkillDao findByLanguageAndLevel(String language, String level) {
        SkillDao skillDao = null;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_LANGUAGE_AND_LEVEL);
             ResultSet resultSet = statement.executeQuery()){
            statement.setString(1,language);
            statement.setString(2,level);
            while (resultSet.next()){
                skillDao = new SkillDao();
                getEntity(resultSet, skillDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select skill by language and level failed");
        }
        return skillDao;
    }

    public Set<String> getListOfLanguage(){
        Set<String> list = new HashSet<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(LIST_OF_LANGUAGE);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()){
                list.add(resultSet.getString("programming_language"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Request failed!");
        }
        return list;
    }

    public Set<String> getListOfLevel(){
        Set<String> list = new HashSet<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(LIST_OF_LEVEL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()){
                list.add(resultSet.getString("skill_level"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Request failed!");
        }
        return list;
    }

    private void getEntity(ResultSet resultSet, SkillDao skillDao) throws SQLException {
        skillDao.setId(resultSet.getInt("id_skill"));
        skillDao.setProgrammingLanguage(resultSet.getString("programming_language"));
        skillDao.setSkillLevel(resultSet.getString("skill_level"));
    }
}
