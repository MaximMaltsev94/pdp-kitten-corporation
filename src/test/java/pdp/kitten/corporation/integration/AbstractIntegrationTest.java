package pdp.kitten.corporation.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;

import static pdp.kitten.corporation.repository.sql.TableConstants.*;

@ContextConfiguration("classpath:servlet-context-text.xml")
@WebAppConfiguration
public abstract class AbstractIntegrationTest extends AbstractTestNGSpringContextTests {
    protected MockMvc mockMvc;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected WebApplicationContext wac;

    @BeforeMethod(groups = "integration")
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        JdbcTestUtils.deleteFromTables(jdbcTemplate, KITTENS_TABLE, JOB_TITLES_TABLE, DEPARTMENTS_TABLE);
        System.out.println("**********************SETUP**********************");
    }
}
