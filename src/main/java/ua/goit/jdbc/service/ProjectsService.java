package ua.goit.jdbc.service;

import ua.goit.jdbc.model.dao.ProjectDao;
import ua.goit.jdbc.model.dto.ProjectDto;
import ua.goit.jdbc.repository.ProjectRepository;
import ua.goit.jdbc.service.converter.ProjectConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectsService implements Service<ProjectDto>{
    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;

    public ProjectsService(ProjectRepository projectRepository, ProjectConverter projectConverter) {
        this.projectRepository = projectRepository;
        this.projectConverter = projectConverter;
    }


    @Override
    public ProjectDto save(ProjectDto entity) {
        ProjectDao projectDao = projectRepository.save(projectConverter.to(entity));
        return projectConverter.from(projectDao);
    }

    @Override
    public ProjectDto update(ProjectDto entity) {
        ProjectDao projectDao = projectRepository.update(projectConverter.to(entity));
        return projectConverter.from(projectDao);
    }

    @Override
    public void delete(ProjectDto entity) {
        projectRepository.delete(projectConverter.to(entity));
    }

    @Override
    public Optional<ProjectDto> findById(Integer id) {
        Optional<ProjectDao> projectDao = projectRepository.findById(id);
        return projectDao.map(projectConverter::from);
    }

    @Override
    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream()
                .map(projectConverter::from)
                .collect(Collectors.toList());
    }

    public boolean isExist(String name) {
        return projectRepository.findByName(name).isPresent();
    }

    public ProjectDto findByName(String name) {
        return projectConverter.from(projectRepository.findByName(name).orElseThrow());
    }

    public Integer getSalaryOfDevelopersFromProject(String projectName){
        return projectRepository.getSalaryOfDevelopersFromProject(projectName);
    }

    public List<String> getListOfDevelopers(String projectName){
        return projectRepository.getListOfDevelopers(projectName);
    }

    public List<String> getListOfProjects(){
        return projectRepository.getListOfProjects();
    }

    public List<String> getListProject(){
        return projectRepository.getListProject();
    }

    public ProjectDto getById(Integer id){
        ProjectDao projectDao = projectRepository.getById(id);
        return projectConverter.from(projectDao);
    }
}
