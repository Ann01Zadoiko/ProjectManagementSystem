package ua.goit.jdbc.service;

import ua.goit.jdbc.model.dao.SkillDao;
import ua.goit.jdbc.model.dto.SkillDto;
import ua.goit.jdbc.repository.SkillRepository;
import ua.goit.jdbc.service.converter.SkillConverter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SkillsService implements Service<SkillDto>{
    private final SkillRepository skillRepository;
    private final SkillConverter skillConverter;


    public SkillsService(SkillRepository skillRepository, SkillConverter skillConverter) {
        this.skillRepository = skillRepository;
        this.skillConverter = skillConverter;
    }


    @Override
    public SkillDto save(SkillDto entity) {
        SkillDao skillDao = skillRepository.save(skillConverter.to(entity));
        return skillConverter.from(skillDao);
    }

    @Override
    public SkillDto update(SkillDto entity) {
        SkillDao skillDao = skillRepository.update(skillConverter.to(entity));
        return skillConverter.from(skillDao);
    }

    @Override
    public void delete(SkillDto entity) {
        skillRepository.delete(skillConverter.to(entity));
    }

    @Override
    public SkillDto findById(Integer id) {
        SkillDao skillDao = skillRepository.findById(id);
        return skillConverter.from(skillDao);
    }

    @Override
    public List<SkillDto> findAll() {
        return skillRepository.findAll().stream()
                .map(skillConverter::from)
                .collect(Collectors.toList());
    }

    public boolean isExist(String language, String level) {
        if (!skillRepository.findByLanguageAndLevel(language, level).equals(null))
            return true;
        else return false;
    }

    public SkillDto findByLanguageAndLevel(String language, String level){
        SkillDao skillDao = skillRepository.findByLanguageAndLevel(language, level);
        return skillConverter.from(skillDao);
    }

    public Set<String> getListOfLanguage(){
        return skillRepository.getListOfLanguage();
    }

    public Set<String> getListOfLevel(){
        return skillRepository.getListOfLevel();
    }
}
