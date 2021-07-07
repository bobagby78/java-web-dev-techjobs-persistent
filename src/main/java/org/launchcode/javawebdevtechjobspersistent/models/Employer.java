package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotEmpty (message = "Location is a required field.")
    @Size (min =3, max = 50, message = "Location needs to be between 3 and 50 characters")
    private String location;

    @OneToMany //Employer is the "owner" side of the Employer/Job relationship.
    @JoinColumn (name= "primary_key", referencedColumnName="foreign_key")
    private List<Job> jobs= new ArrayList<>();

    public Employer(String location) {
        this.location = location;
    }

    public Employer(){};

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

}
