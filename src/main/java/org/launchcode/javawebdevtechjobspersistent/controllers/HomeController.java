package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
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

//Use @Autowired the repositories to set them up with the DB
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                   Errors errors, Model model
//                                   ,@RequestParam int employerId
                                   ,@RequestParam (required = false) Integer skillId
                                   //,@RequestParam (required = false) List<Integer> skills
                                   ) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
//            model.addAttribute("errors", "");
            return "add";
        }

        /*
        TODO: Step 4 of Section 3 in the assignment.
        TODO: this needs to add the employer to job using the RequestParam employerID
        TODO: and the skill, so lather, rinse, repeat

        TODO: Check the functionality of adding tags to events in coding-events-demo
        TODO: It also looks like the @ReqParam might be in the wrong place??
         */
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        return "view";
    }


}
