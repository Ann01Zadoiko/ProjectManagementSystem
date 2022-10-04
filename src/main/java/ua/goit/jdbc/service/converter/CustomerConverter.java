package ua.goit.jdbc.service.converter;

import ua.goit.jdbc.model.dao.CustomerDao;
import ua.goit.jdbc.model.dto.CustomerDto;

public class CustomerConverter implements Converter<CustomerDto, CustomerDao>{
    @Override
    public CustomerDto from(CustomerDao entity) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(entity.getId());
        customerDto.setName(entity.getName());
        customerDto.setCountry(entity.getCountry());
        return customerDto;
    }

    @Override
    public CustomerDao to(CustomerDto entity) {
        CustomerDao customerDao = new CustomerDao();
        customerDao.setId(entity.getId());
        customerDao.setName(entity.getName());
        customerDao.setCountry(entity.getCountry());
        return customerDao;
    }
}
