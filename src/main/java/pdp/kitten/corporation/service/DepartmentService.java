package pdp.kitten.corporation.service;

import pdp.kitten.corporation.domain.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();

    Department get(String id);

    void delete(String id);

    Department save(Department department);
}
