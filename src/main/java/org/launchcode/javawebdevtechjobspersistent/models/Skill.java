package org.launchcode.javawebdevtechjobspersistent.models;


import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Skill extends AbstractEntity {

    @NotEmpty(message = "Please be sure to enter required job skills")
    @Size(min = 3, max = 500, message = "Not to exceed 500 characters")
    private String necessarySkills;

    public Skill(String necessarySkills) {
        this.necessarySkills = necessarySkills;
    }
    public Skill() {};

    public String getNecessarySkills() {
        return necessarySkills;
    }

    public void setNecessarySkills(String necessarySkills) {
        this.necessarySkills = necessarySkills;
    }
}