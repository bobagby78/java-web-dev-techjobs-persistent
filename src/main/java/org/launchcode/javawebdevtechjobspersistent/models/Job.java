package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Job extends AbstractEntity{

    @ManyToOne
    private Employer employer;

    @ManyToMany
    private List<Skill> skills; //change me to a Skill Data Type instead of a String

    public Job() {}

    public Job(Employer employer, List<Skill> skills) {
        this.employer = employer;
        this.skills = skills;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
