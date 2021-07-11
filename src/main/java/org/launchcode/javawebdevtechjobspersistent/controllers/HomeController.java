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


    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;



    @GetMapping
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add") //everything here works as expected
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job"); //on the view side, title is in he header, so it comes from fragments.html
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll()); //add.html uses this to populate the dropdown
        model.addAttribute("skills", skillRepository.findAll()); //add.html uses this to populate the checkboxes
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    @RequestParam(required = false) Integer employerId,
                                    @RequestParam(required = false) List<Integer> skills,
                                    Errors errors, Model model
                                    ) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkill(skillObjs);
        Optional<Employer> optionalEmployer = employerRepository.findById(employerId);
        if (optionalEmployer.isPresent()){
            Employer employer = optionalEmployer.get();
            newJob.setEmployer(employer);
        }

        jobRepository.save(newJob);
        return "redirect:";
    }

    @GetMapping("view/{jobId}") //all good in the hood.
    public String displayViewJob(Model model, @PathVariable int jobId) {

        return "view";
    }

}
