package ua.goit.jdbc.service.converter;

import ua.goit.jdbc.model.dao.ProjectDao;
import ua.goit.jdbc.model.dto.ProjectDto;

import java.sql.Date;

public class ProjectConverter implements Converter<ProjectDto, ProjectDao>{
    @Override
    public ProjectDto from(ProjectDao entity) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(entity.getId());
        projectDto.setName(entity.getName());
        projectDto.setCost(entity.getCost());
        projectDto.setDateCreate(entity.getDateCreate());
        return projectDto;
    }

    @Override
    public ProjectDao to(ProjectDto entity) {
        ProjectDao projectDao = new ProjectDao();
        projectDao.setId(entity.getId());
        projectDao.setName(entity.getName());
        projectDao.setCost(entity.getCost());
        projectDao.setDateCreate( entity.getDateCreate());
        return projectDao;
    }
}
