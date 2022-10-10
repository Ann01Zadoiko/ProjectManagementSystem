package ua.goit.jdbc.service;

import ua.goit.jdbc.model.dao.CustomerDao;
import ua.goit.jdbc.model.dto.CustomerDto;
import ua.goit.jdbc.repository.CustomerRepository;
import ua.goit.jdbc.service.converter.CustomerConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomersService implements Service<CustomerDto>{
    private final CustomerRepository repository;
    private final CustomerConverter converter;

    public CustomersService(CustomerRepository repository, CustomerConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public CustomerDto save(CustomerDto entity) {
        CustomerDao customerDao = repository.save(converter.to(entity));
        return converter.from(customerDao);
    }

    @Override
    public CustomerDto update(CustomerDto entity) {
        CustomerDao customerDao = repository.update(converter.to(entity));
        return converter.from(customerDao);
    }

    @Override
    public void delete(CustomerDto entity) {
        repository.delete(converter.to(entity));
    }

    @Override
    public Optional<CustomerDto> findById(Integer id) {
        Optional<CustomerDao> customerDao = repository.findById(id);
        return customerDao.map(converter::from);
    }

    @Override
    public List<CustomerDto> findAll() {
        return repository.findAll().stream()
                .map(converter::from)
                .collect(Collectors.toList());
    }

    public boolean isExist(String name) {
        return repository.findByName(name).isPresent();
    }

    public CustomerDto findByName(String name) {
      return converter.from(repository.findByName(name).orElseThrow());
    }
}
