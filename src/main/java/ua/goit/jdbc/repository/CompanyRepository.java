package ua.goit.jdbc.repository;

import ua.goit.jdbc.config.DataManagerConnector;
import ua.goit.jdbc.model.dao.CompanyDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository implements Repository<CompanyDao>{
    private final DataManagerConnector connector;
    private static final String INSERT = "INSERT into companies (name_company, country) values (?,?)";
    private static final String SELECT_BY_ID = "SELECT id_company, name_company, country from companies where id_company = ?";
    private static final String UPDATE = "UPDATE companies set name_company = ?, country = ? where id_company = ? ";
    private static final String DELETE = "DELETE from companies where id_company = ?";
    private static final String SELECT_ALL = "SELECT id_company, name_company, country from companies";
    private static final String SELECT_BY_NAME = "SELECT id_company, name_company, country from companies where name_company = ?";

    public CompanyRepository(DataManagerConnector connector) {
        this.connector = connector;
    }


    @Override
    public CompanyDao save(CompanyDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getCountry());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating company failed, no ID obtained.");
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Company is not created");
        }
        return entity;
    }

    @Override
    public CompanyDao update(CompanyDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getCountry());
            statement.setInt(3, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Company is not updated");
        }
        return entity;
    }

    @Override
    public CompanyDao findById(Integer id) {
        CompanyDao companyDao = null;
        try(Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        ResultSet resultSet = statement.executeQuery()){
            statement.setInt(1,id);
            while (resultSet.next()){
                 companyDao = new CompanyDao();
                getEntity(resultSet, companyDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select company by id failed");
        }
        return companyDao;
    }

    @Override
    public List<CompanyDao> findAll() {
        List<CompanyDao> companyDaos = new ArrayList<>();
        try (Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                CompanyDao companyDao = new CompanyDao();
                getEntity(resultSet, companyDao);
                companyDaos.add(companyDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select all companies failed");
        }
        return companyDaos;
    }

    @Override
    public void delete(CompanyDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, entity.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Company is not deleted");
        }
    }

    public CompanyDao findByName(String name) {
        CompanyDao companyDao = null;
        try (Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME);
        ResultSet resultSet = statement.executeQuery()){
            statement.setString(1,name);
            while (resultSet.next()){
                companyDao = new CompanyDao();
                getEntity(resultSet, companyDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select company by name failed");
        }
        return companyDao;
    }



    private void getEntity(ResultSet resultSet, CompanyDao companyDao) throws SQLException {
        companyDao.setId(resultSet.getInt("id_company"));
        companyDao.setName(resultSet.getString("name_company"));
        companyDao.setCountry(resultSet.getString("country"));
    }
}
