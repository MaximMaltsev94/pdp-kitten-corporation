package pdp.kitten.corporation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pdp.kitten.corporation.domain.Department;
import pdp.kitten.corporation.service.DepartmentService;
import pdp.kitten.corporation.service.JobTitleService;

import java.util.List;

@Controller
public class IndexController {

    private DepartmentService departmentService;
    private JobTitleService jobTitleService;

    @Autowired
    public IndexController(DepartmentService departmentService, JobTitleService jobTitleService) {
        this.departmentService = departmentService;
        this.jobTitleService = jobTitleService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("jobTitles", jobTitleService.getAll());
        return "index";
    }
}
