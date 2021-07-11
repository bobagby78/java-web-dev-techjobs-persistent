package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

    //add job field here. think about the type of object(arraylist containing Job objects [Jobjects])

    @ManyToMany(mappedBy = "skill") //this originally said skills (according to the text), but I think that was wrong as it's reaching into Job and looking for a related field, which is called skill.
    private List<Job> jobs = new ArrayList<>();

    @NotEmpty(message = "Please be sure to enter a skill description")
    @Size(min = 3, max = 500, message = "Not to exceed 500 characters")
    private String description;

    public Skill(String description) {
        this.description = description;
    }

    public Skill() {};

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}