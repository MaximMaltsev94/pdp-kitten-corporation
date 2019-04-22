package pdp.kitten.corporation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pdp.kitten.corporation.domain.Department;
import pdp.kitten.corporation.service.DepartmentService;
import pdp.kitten.corporation.service.KittenService;

@Controller
@RequestMapping("/departments")
public class DepartmentsController {

    private DepartmentService departmentService;

    private KittenService kittenService;

    public DepartmentsController(DepartmentService departmentService, KittenService kittenService) {
        this.departmentService = departmentService;
        this.kittenService = kittenService;
    }

    @GetMapping
    public String departmentHome() {
        return "redirect:/";
    }

    @GetMapping("{departmentId}")
    public String departmentPage(@PathVariable String departmentId, Model model) {
        Department department = departmentService.get(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("kittens", kittenService.getAll());
        return "department";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("department") Department department) {
        departmentService.save(department);
        return "redirect:/departments/" + department.getId() ;
    }

    @PostMapping("/delete/{departmentId}")
    public String delete(@PathVariable String departmentId) {
        departmentService.delete(departmentId);
        return "redirect:/";
    }
}
