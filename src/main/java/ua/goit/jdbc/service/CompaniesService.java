package ua.goit.jdbc.service;

import ua.goit.jdbc.model.dao.CompanyDao;
import ua.goit.jdbc.model.dto.CompanyDto;
import ua.goit.jdbc.repository.CompanyRepository;
import ua.goit.jdbc.service.converter.CompanyConverter;

import java.util.List;
import java.util.stream.Collectors;

public class CompaniesService implements Service<CompanyDto>{
    private final CompanyRepository repository;
    private final CompanyConverter converter;

    public CompaniesService(CompanyRepository repository, CompanyConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public CompanyDto save(CompanyDto entity) {
        CompanyDao companyDao = repository.save(converter.to(entity));
        return converter.from(companyDao);
    }

    @Override
    public CompanyDto update(CompanyDto entity) {
        CompanyDao companyDao = repository.update(converter.to(entity));
        return converter.from(companyDao);
    }

    @Override
    public void delete(CompanyDto entity) {
        repository.delete(converter.to(entity));
    }

    @Override
    public CompanyDto findById(Integer id) {
        CompanyDao companyDao = repository.findById(id);
        return converter.from(companyDao);
    }

    @Override
    public List<CompanyDto> findAll() {
        return repository.findAll().stream()
                .map(converter::from)
                .collect(Collectors.toList());
    }

    public boolean isExist(String name) {
        if (!repository.findByName(name).equals(null)){
            return true;
        } else
            return false;
    }

    public CompanyDto findByName(String name) {
        CompanyDao companyDao = repository.findByName(name);
        return converter.from(companyDao);
    }
}
