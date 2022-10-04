package ua.goit.jdbc.service.converter;

import ua.goit.jdbc.model.dao.SkillDao;
import ua.goit.jdbc.model.dto.SkillDto;

public class SkillConverter implements Converter<SkillDto, SkillDao>{
    @Override
    public SkillDto from(SkillDao entity) {
        SkillDto skillDto = new SkillDto();
        skillDto.setId(entity.getId());
        skillDto.setProgrammingLanguage(entity.getProgrammingLanguage());
        skillDto.setSkillLevel(entity.getSkillLevel());
        return skillDto;
    }

    @Override
    public SkillDao to(SkillDto entity) {
        SkillDao skillDao = new SkillDao();
        skillDao.setId(entity.getId());
        skillDao.setProgrammingLanguage(entity.getProgrammingLanguage());
        skillDao.setSkillLevel(entity.getSkillLevel());
        return skillDao;
    }
}
