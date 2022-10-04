package ua.goit.jdbc.model.dao;

public class SkillDao {
    Integer id;
    String programmingLanguage;
    String skillLevel;

    public SkillDao(){}

    public SkillDao(Integer id, String programmingLanguage, String skillLevel) {
        this.id = id;
        this.programmingLanguage = programmingLanguage;
        this.skillLevel = skillLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    @Override
    public String toString() {
        return "SkillDao{" +
                "id=" + id +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                ", skillLevel='" + skillLevel + '\'' +
                '}';
    }
}
