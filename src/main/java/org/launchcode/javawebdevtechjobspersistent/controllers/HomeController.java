package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired //tells hibernate:"Hey, if this is a thing, I want to use it" If it's not a thing, your poop out of luck and your app is useless.
    private EmployerRepository employerRepository; //used for CRUDing info to and from the database. CRUD- Create, Read, Update, Delete

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;


    @GetMapping //If a job exists, this is going to throw a link to it on...
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");
        model.addAttribute("jobs", jobRepository.findAll());

        return "index";//... the main index page. localhost:8080 in this case
    }

    @GetMapping("add") // This bit of code uses the database to populate the /add page with the form necessary to add a new job
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job"); //on the view side, title is in he header, so it comes from fragments.html
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll()); // this is used to populate the dropdown with employers
        model.addAttribute("skills", skillRepository.findAll()); //this is used to populate the checkboxes with skills, more than one can be used.
        return "add";
    }

    @PostMapping("add") //uses the form to send information over to the database.
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    @RequestParam(required = false) Integer employerId,
                                    @RequestParam(required = false) List<Integer> skills,
                                    Errors errors, Model model
                                    ) {

        if (errors.hasErrors()) { //If there are errors in the job form...
            model.addAttribute("title", "Add Job");
            return "add"; //... send the user back to the empty form on the /add page
        }//if no errors are found, it just skips this block and goes on to the next.

        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills); //any skills that were checked on the form are flagged by id...
        if (!skillObjs.isEmpty()){ //... if the skills list is NOT empty...
            newJob.setSkills(skillObjs); //... save them as a list to the newJob object
        }

        Optional<Employer> optionalEmployer = employerRepository.findById(employerId); // check for employers by ID
        if (optionalEmployer.isPresent()){ // if one is present...
            Employer employer = optionalEmployer.get(); //... make it an Employer object to inhabit
            newJob.setEmployer(employer);// ... and set the employer in the newJob object
        }

        jobRepository.save(newJob); //save that newJob object to the database
        return "redirect:"; //... and go back to the main page, where the new job should be found.
    }

    @GetMapping("view/{jobId}") //check out the jobs by their id number
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional optJob = jobRepository.findById(jobId);
        if (optJob.isPresent()){
            Job job = (Job) optJob.get();
            model.addAttribute("job", job);

        }

        return "view";
    }

}
