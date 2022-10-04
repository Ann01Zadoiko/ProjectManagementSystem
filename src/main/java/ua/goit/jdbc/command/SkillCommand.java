package ua.goit.jdbc.command;

import ua.goit.jdbc.model.dao.SkillDao;
import ua.goit.jdbc.model.dto.CompanyDto;
import ua.goit.jdbc.model.dto.SkillDto;
import ua.goit.jdbc.service.SkillsService;
import ua.goit.jdbc.view.View;

import java.util.List;

public class SkillCommand implements Command{
    public static final String SKILL_COMMAND = "skill";
    private final View view;
    private final SkillsService service;

    public SkillCommand(View view, SkillsService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(SKILL_COMMAND);
    }

    @Override
    public void execute() {
        view.write("Enter next command (create, read, update or delete):");
        String command = view.read();
        switch (command){
            case "create": update();
            case "read": read();
            case "update": update();
            case "delete": delete();
        }
    }

    public void create(){
        String language, level;
        while (true){
            view.write("Enter programming language");
            language = view.read();
            view.write("Enter skill level");
            level = view.read();
            if (!service.isExist(language,level)) break;
            view.write("Skill already exists.");
        }
        SkillDto skillDto = new SkillDto(language,level);
        service.save(skillDto);
        view.write(String.format("Programming " +
                "language %s added and skill level %s added to database",language,level));
    }

    public void read(){
        view.write("Enter programming language");
        String language = view.read();
        view.write("Enter skill level");
        String level = view.read();
        SkillDto skillDto = service.findByLanguageAndLevel(language,level);
        view.write("Programming language " + skillDto.getProgrammingLanguage() + " and skill level " + skillDto.getSkillLevel());
    }

    public void update(){
        String language, level;
        while (true){
            view.write("Enter programming language");
            language = view.read();
            view.write("Enter skill level");
            level = view.read();
            if (service.isExist(language,level)) break;
            view.write("Skill doesn't exist.");
        }
        SkillDto skillDto = service.findByLanguageAndLevel(language,level);
        view.write("Enter programming language");
        language = view.read();
        view.write("Enter skill level");
        level = view.read();
        if (!language.equals(null)){
            skillDto.setProgrammingLanguage(language);
        }
        if (!level.equals(null)){
            skillDto.setSkillLevel(level);
        }
        service.update(skillDto);
        view.write("Skill data updated");
    }

    public void delete(){
        String language, level;
        while (true){
            view.write("Enter programming language");
            language = view.read();
            view.write("Enter skill level");
            level = view.read();
            if (service.isExist(language,level)) break;
            view.write("Skill doesn't exist.");
        }
        SkillDto skillDto = service.findByLanguageAndLevel(language,level);
        service.delete(skillDto);
        if(!service.isExist(language, level)) {
            view.write(String.format("Programming language %s and skill level %s deleted from database", language, level));
        }
    }
}
