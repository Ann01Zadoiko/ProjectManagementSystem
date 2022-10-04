package ua.goit.jdbc.service.converter;

import ua.goit.jdbc.model.dao.CompanyDao;
import ua.goit.jdbc.model.dto.CompanyDto;

public class CompanyConverter implements Converter<CompanyDto, CompanyDao>{
    @Override
    public CompanyDto from(CompanyDao entity) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(entity.getId());
        companyDto.setName(entity.getName());
        companyDto.setCountry(entity.getCountry());
        return companyDto;
    }

    @Override
    public CompanyDao to(CompanyDto entity) {
        CompanyDao companyDao = new CompanyDao();
        companyDao.setId(entity.getId());
        companyDao.setName(entity.getName());
        companyDao.setCountry(entity.getCountry());
        return companyDao;
    }
}
