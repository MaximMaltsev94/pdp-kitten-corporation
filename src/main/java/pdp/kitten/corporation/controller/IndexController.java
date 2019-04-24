package pdp.kitten.corporation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pdp.kitten.corporation.domain.Department;
import pdp.kitten.corporation.service.DepartmentService;
import pdp.kitten.corporation.service.JobTitleService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Controller
public class IndexController implements InitializingBean, DisposableBean {

    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    
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

    @Override
    public void destroy() throws Exception {
        LOGGER.info("TEST INIT METHODS: IndexController disposableBean()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("TEST INIT METHODS: IndexController initializingBean()");
    }

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("TEST INIT METHODS: IndexController postConstruct()");
    }

    @PreDestroy
    public void preDestroy() {
        LOGGER.info("TEST INIT METHODS: IndexController preDestroy()");
    }
}
