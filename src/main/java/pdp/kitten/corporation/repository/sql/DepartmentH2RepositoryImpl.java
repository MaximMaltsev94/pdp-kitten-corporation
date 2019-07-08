package pdp.kitten.corporation.repository.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pdp.kitten.corporation.domain.Department;
import pdp.kitten.corporation.domain.JobTitle;
import pdp.kitten.corporation.domain.Kitten;
import pdp.kitten.corporation.repository.DepartmentRepository;

import java.sql.Statement;
import java.util.*;

import static pdp.kitten.corporation.repository.sql.TableConstants.*;

@Repository
public class DepartmentH2RepositoryImpl implements DepartmentRepository {

    private static final String SELECT_ALL_DEPARTMENTS = "SELECT dep.id as departmentId, dep.name as departmentName, maxKittenCount, kit.id as kittenID, kit.name as kittenName, kit.age as kittenAge, jobTitleId, tit.name as jobTitleName\n" +
            "        from %s as dep left join %s as kit on dep.id = kit.departmentID left join %s as tit on kit.jobTitleID = tit.id";

    private static final String SELECT_ALL_BY_ID = SELECT_ALL_DEPARTMENTS + " WHERE dep.id = %s";
    private static final String INSERT_DEPARTMENT = "insert into %s (name, maxKittenCount) values('%s', %s)";
    private static final String UPDATE_DEPARTMENT = "update %s\n" +
            "        set name = '%s', maxKittenCount=%s\n" +
            "        where id=%s";
    private static final String DELETE_DEPARTMENT = "delete from %s where id=%s";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ResultSetExtractor<Map<Integer, Department>> findAllResultSetExtractor = rs -> {
        Map<Integer, Department> departments = new HashMap<>();

        while (rs.next()) {
            Integer id = rs.getInt("departmentID");

            Department department = departments.get(id);

            if (department == null) {
                department = new Department(id.toString(),
                        rs.getString("departmentName"),
                        rs.getInt("maxKittenCount"),
                        new ArrayList<>());
                departments.put(id, department);
            }
            if(rs.getString("kittenId") != null) {
                JobTitle jobTitle = null;
                if(rs.getString("jobTitleId") != null) {
                    jobTitle = new JobTitle(
                                    rs.getString("jobTitleId"),
                                    rs.getString("jobTitleName"));
                }

                department.getKittens().add(
                        new Kitten(
                                rs.getString("kittenId"),
                                rs.getString("kittenName"),
                                rs.getInt("kittenAge"),
                                department,
                                jobTitle));
            }
        }
        return departments;
    };

    @Override
    public List<Department> findAll() {
        Map<Integer, Department> departmentMap = jdbcTemplate.query(
                String.format(SELECT_ALL_DEPARTMENTS, DEPARTMENTS_TABLE, KITTENS_TABLE, JOB_TITLES_TABLE),
                findAllResultSetExtractor);

        return new ArrayList<>(departmentMap.values());
    }

    @Override
    public Department find(String id) {
        Map<Integer, Department> departmentMap = jdbcTemplate.query(
                String.format(SELECT_ALL_BY_ID, DEPARTMENTS_TABLE, KITTENS_TABLE, JOB_TITLES_TABLE, id),
                findAllResultSetExtractor);
        return departmentMap.values().iterator().next();
    }

    @Override
    public Department save(Department department) {
        if (department.getId() == null) {
            return insert(department);
        } else {
            return update(department);
        }
    }

    private Department insert(Department department) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (connection) -> connection.prepareStatement(String.format(INSERT_DEPARTMENT, DEPARTMENTS_TABLE, department.getName(), department.getMaxKittenCount()), Statement.RETURN_GENERATED_KEYS), keyHolder);
        department.setId(Objects.toString(keyHolder.getKey()));
        return department;
    }

    private Department update(Department department) {
        jdbcTemplate.update(String.format(UPDATE_DEPARTMENT, DEPARTMENTS_TABLE, department.getName(), department.getMaxKittenCount(), department.getId()));
        return department;
    }

    @Override
    public void delete(String id) {
        jdbcTemplate.update(String.format(DELETE_DEPARTMENT, DEPARTMENTS_TABLE, id));
    }
}
