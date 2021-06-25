package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Employer extends AbstractEntity {

    @NotEmpty (message = "Location is a required field.")
    @Size (min =3, max = 50, message = "Location needs to be between 3 an 50 characters")
    private String location;

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
}
