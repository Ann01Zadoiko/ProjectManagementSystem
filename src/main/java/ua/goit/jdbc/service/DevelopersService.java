package ua.goit.jdbc.service;

import ua.goit.jdbc.model.dao.DeveloperDao;
import ua.goit.jdbc.model.dto.DeveloperDto;
import ua.goit.jdbc.repository.DeveloperRepository;
import ua.goit.jdbc.service.converter.DeveloperConverter;

import java.util.List;
import java.util.stream.Collectors;

public class DevelopersService implements Service<DeveloperDto>{
    private final DeveloperRepository repository;
    private final DeveloperConverter converter;

    public DevelopersService(DeveloperRepository repository, DeveloperConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public DeveloperDto save(DeveloperDto entity) {
        DeveloperDao developerDao = repository.save(converter.to(entity));
        return converter.from(developerDao);
    }

    @Override
    public DeveloperDto update(DeveloperDto entity) {
        DeveloperDao developerDao = repository.update(converter.to(entity));
        return converter.from(developerDao);
    }

    @Override
    public void delete(DeveloperDto entity) {
        repository.delete(converter.to(entity));
    }

    @Override
    public DeveloperDto findById(Integer id) {
        DeveloperDao developerDao = repository.findById(id);
        return converter.from(developerDao);
    }

    @Override
    public List<DeveloperDto> findAll() {
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

    public DeveloperDto findByName(String name) {
        DeveloperDao developerDao = repository.findByName(name);
        return converter.from(developerDao);
    }

    public List<String> getListOfDevelopersByLanguage(String language){
        return repository.getListOfDevelopersByLanguage(language);
    }

    public List<String> getListOfDevelopersBySkillLevel(String skillLevel){
        return repository.getListOfDevelopersBySkillLevel(skillLevel);
    }
}
