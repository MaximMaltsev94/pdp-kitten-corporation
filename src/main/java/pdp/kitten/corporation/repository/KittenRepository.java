package pdp.kitten.corporation.repository;

import pdp.kitten.corporation.domain.Kitten;

import java.util.List;

public interface KittenRepository extends CrudRepository<String, Kitten> {
    void setKittenDepartment(String kittenId, String departmentId);
}
