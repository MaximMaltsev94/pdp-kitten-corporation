package pdp.kitten.corporation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.kitten.corporation.domain.Department;
import pdp.kitten.corporation.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department get(String id) {
        return departmentRepository.find(id);
    }

    public void delete(String id) {
        departmentRepository.delete(id);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }
}
