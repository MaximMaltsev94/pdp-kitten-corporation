package pdp.kitten.corporation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pdp.kitten.corporation.domain.JobTitle;
import pdp.kitten.corporation.service.JobTitleService;

@Controller
@RequestMapping("/job-titles")
public class JobTitleController {

    private JobTitleService jobTitleService;

    @Autowired
    public JobTitleController(JobTitleService jobTitleService) {
        this.jobTitleService = jobTitleService;
    }

    @GetMapping
    public String jobTitlesHome(Model model) {
        model.addAttribute("jobTitles", jobTitleService.getAll());
        return "job-titles";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("name") String name) {
        jobTitleService.save(new JobTitle(null, name));
        return "redirect:/job-titles";
    }

    @PostMapping("/delete/{jobTitleId}")
    public String deleteJobTitle(@PathVariable String jobTitleId) {
        jobTitleService.delete(jobTitleId);
        return "redirect:/job-titles";
    }

}
