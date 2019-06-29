package pdp.kitten.corporation.repository.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pdp.kitten.corporation.domain.Department;
import pdp.kitten.corporation.domain.JobTitle;
import pdp.kitten.corporation.domain.Kitten;
import pdp.kitten.corporation.repository.KittenRepository;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class KittenH2RepositoryImpl implements KittenRepository {

    private static final String JOB_TITLES_TABLE = "KITTEN_CORPORATION.JOB_TITLES";
    private static final String DEPARTMENTS_TABLE = "KITTEN_CORPORATION.DEPARTMENTS";
    private static final String KITTENS_TABLE = "KITTEN_CORPORATION.KITTENS";

    private static final String SELECT_ALL_KITTENS = "SELECT dep.id as departmentId, dep.name as departmentName, maxKittenCount, kit.id as kittenID, kit.name as kittenName, kit.age as kittenAge, jobTitleId, tit.name as jobTitleName\n" +
            "        from  %s as dep right join %s as kit on dep.id = kit.departmentID left join %s as tit on kit.jobTitleID = tit.id";

    private static final String SELECT_ALL_BY_ID = SELECT_ALL_KITTENS + " WHERE id = %d";
    private static final String INSERT_KITTEN = "INSERT INTO %s (name, age, departmentId, jobTitleId) VALUES('%s', %s, %s, %s)";
    private static final String UPDATE_KITTEN = "UPDATE %s SET name='%s', age=%s, departmentId=%s, jobTitleId=%s WHERE id=%s";
    private static final String DELETE_KITTEN = "DELETE FROM ? WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Kitten> kittenRowMapper = (rs, i) -> {

        Integer departmentId = rs.getInt("departmentId");

        Department department = null;

        if (departmentId != null) {
            department = new Department(departmentId.toString(),
                    rs.getString("departmentName"),
                    rs.getInt("maxKittenCount"),
                    new ArrayList<>());
        }

        JobTitle jobTitle = null;
        if(rs.getString("jobTitleId") != null) {
            jobTitle = new JobTitle(
                    rs.getString("jobTitleId"),
                    rs.getString("jobTitleName"));
        }

        return new Kitten(
                        rs.getString("kittenId"),
                        rs.getString("kittenName"),
                        rs.getInt("kittenAge"),
                        department,
                        jobTitle);
    };

    @Override
    public void setKittenDepartment(String kittenId, String departmentId) {

    }

    @Override
    public List<Kitten> findAll() {
        return jdbcTemplate.query(
                String.format(SELECT_ALL_KITTENS, DEPARTMENTS_TABLE, KITTENS_TABLE, JOB_TITLES_TABLE),
                kittenRowMapper);
    }

    @Override
    public Kitten find(String id) {
        return jdbcTemplate.queryForObject(
                String.format(SELECT_ALL_BY_ID, DEPARTMENTS_TABLE, KITTENS_TABLE, JOB_TITLES_TABLE, id),
                kittenRowMapper);
    }

    @Override
    public Kitten save(Kitten kitten) {
        if(kitten.getId() == null) {
            return insert(kitten);
        } else {
            return update(kitten);
        }
    }

    private Kitten insert(Kitten kitten) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (connection) -> connection.prepareStatement(
                        String.format(INSERT_KITTEN, KITTENS_TABLE,
                                kitten.getName(),
                                kitten.getAge(),
                                kitten.getDepartment().getId(),
                                kitten.getJobTitle().getId()
                        ), Statement.RETURN_GENERATED_KEYS), keyHolder);
        kitten.setId(Objects.toString(keyHolder.getKey()));
        return kitten;
    }

    private Kitten update(Kitten kitten) {
        jdbcTemplate.update(String.format(UPDATE_KITTEN, KITTENS_TABLE,
                kitten.getName(),
                kitten.getAge(),
                kitten.getDepartment().getId(),
                kitten.getJobTitle().getId()));
        return kitten;
    }

    @Override
    public void delete(String id) {
        jdbcTemplate.update(DELETE_KITTEN, KITTENS_TABLE, id);
    }
}
