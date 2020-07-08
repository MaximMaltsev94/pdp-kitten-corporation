package pdp.kitten.corporation.integration;

import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pdp.kitten.corporation.domain.Department;
import pdp.kitten.corporation.domain.JobTitle;
import pdp.kitten.corporation.domain.Kitten;
import pdp.kitten.corporation.repository.DepartmentRepository;
import pdp.kitten.corporation.repository.JobTitleRepository;
import pdp.kitten.corporation.repository.KittenRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetKittenHomePage extends AbstractIntegrationTest {

    @Autowired
    private JobTitleRepository jobTitleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private KittenRepository kittenRepository;

    @BeforeMethod(groups = "integration")
    public void insertData() {
        jobTitleRepository.save(new JobTitle(null, "testJobTitle1"));
        jobTitleRepository.save(new JobTitle(null, "testJobTitle2"));
        jobTitleRepository.save(new JobTitle(null, "testJobTitle3"));

        departmentRepository.save(new Department(null, "testDepartment1", 10, null));
        departmentRepository.save(new Department(null, "testDepartment2", 20, null));
        departmentRepository.save(new Department(null, "testDepartment3", 30, null));
        departmentRepository.save(new Department(null, "testDepartment4", 40, null));
    }

    @Test(groups = "integration")
    public void testName() throws Exception {
        mockMvc.perform(get("/kittens/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("kittens"))
                .andExpect(MockMvcResultMatchers.model().attribute("jobTitles", Matchers.iterableWithSize(3)))
                .andExpect(MockMvcResultMatchers.model().attribute("departments", Matchers.iterableWithSize(4)));
    }
}
