package pdp.kitten.corporation.repository.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pdp.kitten.corporation.domain.JobTitle;
import pdp.kitten.corporation.repository.JobTitleRepository;

import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import static pdp.kitten.corporation.repository.sql.TableConstants.JOB_TITLES_TABLE;

@Repository
public class JobTitleH2RepositoryImpl implements JobTitleRepository {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM %s";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM %s WHERE id=%s";
    private static final String INSERT_JOB_TITLE = "INSERT INTO %s (name) values('%s')";
    private static final String UPDATE_JOB_TITLE = "UPDATE %s SET name = '%s' WHERE id=%s";
    private static final String DELETE_JOB_TITLE = "DELETE FROM %s WHERE id=%s";

    private final RowMapper<JobTitle> jobTitleRowMapper = (row, i) -> {
        String id = Integer.toString(row.getInt("id"));
        String name = row.getNString("name");
        return new JobTitle(id, name);
    };

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<JobTitle> findAll() {
        return jdbcTemplate.query(String.format(SELECT_ALL_QUERY, JOB_TITLES_TABLE), jobTitleRowMapper);
    }

    @Override
    public JobTitle find(String id) {
        return jdbcTemplate.queryForObject(String.format(SELECT_BY_ID_QUERY, JOB_TITLES_TABLE, id), jobTitleRowMapper);
    }

    @Override
    public JobTitle save(JobTitle jobTitle) {
        if(jobTitle.getId() == null) {
            return insert(jobTitle);
        } else {
            return update(jobTitle);
        }
    }

    private JobTitle insert(JobTitle jobTitle) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (connection) -> connection.prepareStatement(String.format(INSERT_JOB_TITLE, JOB_TITLES_TABLE, jobTitle.getName()), Statement.RETURN_GENERATED_KEYS), keyHolder);
        jobTitle.setId(Objects.toString(keyHolder.getKey()));
        return jobTitle;
    }

    private JobTitle update(JobTitle jobTitle) {
        jdbcTemplate.update(String.format(UPDATE_JOB_TITLE, JOB_TITLES_TABLE, jobTitle.getName(), jobTitle.getId()));
        return jobTitle;
    }

    @Override
    public void delete(String id) {
        jdbcTemplate.update(String.format(DELETE_JOB_TITLE, JOB_TITLES_TABLE, id));
    }
}
