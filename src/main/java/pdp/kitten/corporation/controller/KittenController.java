package pdp.kitten.corporation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pdp.kitten.corporation.domain.Kitten;
import pdp.kitten.corporation.service.DepartmentService;
import pdp.kitten.corporation.service.JobTitleService;
import pdp.kitten.corporation.service.KittenService;

@Controller
@RequestMapping("/kittens")
public class KittenController {

    private KittenService kittenService;

    private DepartmentService departmentService;

    private JobTitleService jobTitleService;

    @Autowired
    public KittenController(KittenService kittenService, DepartmentService departmentService, JobTitleService jobTitleService) {
        this.kittenService = kittenService;
        this.departmentService = departmentService;
        this.jobTitleService = jobTitleService;
    }

    @GetMapping
    public String kittensHome(Model model) {
        model.addAttribute("kittens", kittenService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("jobTitles", jobTitleService.getAll());
        return "kittens";
    }

    @PostMapping("/save")
    public String create(@ModelAttribute("kitten") Kitten kitten) {
        kittenService.save(kitten);
        return "redirect:/kittens/";
    }

    @PostMapping("/set-kitten-department")
    public String setKittenDepartment(String kittenId, String departmentId) {
        kittenService.setKittenDepartment(kittenId, departmentId);
        return "redirect:/departments/" + departmentId;
    }

    @PostMapping("/delete/{kittenId}")
    public String deleteKitten(@PathVariable String kittenId) {
        kittenService.delete(kittenId);
        return "redirect:/kittens";
    }
}
