package pdp.kitten.corporation.service;

import pdp.kitten.corporation.domain.Kitten;

import java.util.List;

public interface KittenService {

    List<Kitten> getAll();

    Kitten get(String id);

    void delete(String id);

    void save(Kitten kitten);

    void setKittenDepartment(String kittenId, String departmentId);
}
