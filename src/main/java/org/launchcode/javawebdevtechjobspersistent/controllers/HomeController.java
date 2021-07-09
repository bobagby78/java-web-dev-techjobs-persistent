package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Job;
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

    @PostMapping("add") //not adding employer id to the job table???
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model
                                    , @RequestParam int employerId //, @RequestParam List<Integer> skills //Did I even Put this here for a reason?
                                    ) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }
        jobRepository.save(newJob);
        return "redirect:";
    }

    @GetMapping("view/{jobId}") //all good in the hood.
    public String displayViewJob(Model model, @PathVariable int jobId) {

        return "view";
    }

}
