package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping
    public String displayAllEmployers (Model model){
        model.addAttribute("title", "All Employers"); // Sets the view up with attribute "title" set to "All Employers" to put the title on the employers/index page
        model.addAttribute("employers", employerRepository.findAll()); //the template to show the employers uses the attribute "employers"  and the method .findAll() to pull all the employers in the mySQL employers table (AKA all of the entries in the repository of Employers).
        return "employers/index";
    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) { //sets up the employers/add.html template to accept a new Employer, automatically requiring all the necessary fields within
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add") //when an employer is added through the form, this pushes the database via employerRepository
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "employers/add";
        }

        employerRepository.save(newEmployer);

        return "redirect:";
    }

    @GetMapping("view/{employerId}") // displays the employers that have been created. pulls the database info with employerRepository.findById. employerId is the value set by the view.
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) { //if an employer exists in the database...
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer); //bring it into the view using the attribute employer
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }


}
