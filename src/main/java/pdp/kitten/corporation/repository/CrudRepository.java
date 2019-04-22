package pdp.kitten.corporation.repository;

import java.util.List;

public interface CrudRepository<ID, ENTITY> {

    List<ENTITY> findAll();

    ENTITY find(ID id);

    ENTITY save(ENTITY entity);

    void delete(ID id);
}
