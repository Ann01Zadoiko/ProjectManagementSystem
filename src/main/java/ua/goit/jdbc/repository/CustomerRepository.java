package ua.goit.jdbc.repository;

import ua.goit.jdbc.config.DataManagerConnector;
import ua.goit.jdbc.model.dao.CustomerDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements Repository<CustomerDao>{
    private final DataManagerConnector connector;
    private static final String INSERT = "INSERT into customers (name_customer, country) values (?,?)";
    private static final String SELECT_BY_ID = "SELECT id_customer, name_customer, country from customers where id_customer = ?";
    private static final String UPDATE = "UPDATE customers set name_customer = ?, country = ? where id_customer = ? ";
    private static final String DELETE = "DELETE customers customers where id_customer = ? ";
    private static final String SELECT_ALL = "SELECT id_customer, name_customer, country from customers";
    private static final String SELECT_BY_NAME = "SELECT id_customer, name_customer, country from customers where name_customer = ?";

    public CustomerRepository(DataManagerConnector connector) {
        this.connector = connector;
    }

    @Override
    public CustomerDao save(CustomerDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
             statement.setString(1, entity.getName());
             statement.setString(2, entity.getCountry());
             statement.executeUpdate();
             try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating customer failed, no ID obtained.");
                }
             }
        } catch (SQLException e) {
             e.printStackTrace();
             throw new RuntimeException("Customer is not created");
        }
        return entity;
    }

    @Override
    public CustomerDao update(CustomerDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
             statement.setString(1, entity.getName());
             statement.setString(2, entity.getCountry());
             statement.setInt(3, entity.getId());
             statement.executeUpdate();
        } catch (SQLException e) {
             e.printStackTrace();
             throw new RuntimeException("Customer is not updated");
        }
        return entity;
    }

    @Override
    public CustomerDao findById(Integer id) {
        CustomerDao customerDao = null;
        try (Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        ResultSet resultSet = statement.executeQuery()){
            statement.setInt(1,id);
            while (resultSet.next()){
                customerDao = new CustomerDao();
                getEntity(resultSet, customerDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select customer by id failed");
        }
        return customerDao;
    }

    @Override
    public List<CustomerDao> findAll() {
        List<CustomerDao> customerDaos = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                CustomerDao customerDao = new CustomerDao();
                getEntity(resultSet, customerDao);
                customerDaos.add(customerDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select all customers failed");
        }
        return customerDaos;
    }

    @Override
    public void delete(CustomerDao entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
             statement.setInt(1, entity.getId());
             statement.execute();
        } catch (SQLException e) {
             e.printStackTrace();
             throw new RuntimeException("Customer is not deleted");
        }
    }


    public CustomerDao findByName(String name) {
        CustomerDao customerDao = null;
        try (Connection connection = connector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME);
        ResultSet resultSet = statement.executeQuery()){
            statement.setString(1, name);
            while (resultSet.next()){
                customerDao = new CustomerDao();
                getEntity(resultSet, customerDao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Select customer by name failed");
        }
        return customerDao;
    }

    private void getEntity(ResultSet resultSet, CustomerDao customerDao) throws SQLException {
        customerDao.setId(resultSet.getInt("id_customer"));
        customerDao.setName(resultSet.getString("name_customer"));
        customerDao.setCountry(resultSet.getString("country"));
    }
}
