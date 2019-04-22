package pdp.kitten.corporation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.kitten.corporation.domain.Kitten;
import pdp.kitten.corporation.repository.KittenRepository;

import java.util.List;

@Service
public class KittenServiceImpl implements KittenService {

    private KittenRepository kittenRepository;

    @Autowired
    public KittenServiceImpl(KittenRepository kittenRepository) {
        this.kittenRepository = kittenRepository;
    }

    @Override
    public List<Kitten> getAll() {
        return kittenRepository.findAll();
    }

    @Override
    public Kitten get(String id) {
        return kittenRepository.find(id);
    }

    @Override
    public void delete(String id) {
        kittenRepository.delete(id);
    }

    @Override
    public void save(Kitten kitten) {
        kittenRepository.save(kitten);
    }

    @Override
    public void setKittenDepartment(String kittenId, String departmentId) {
        kittenRepository.setKittenDepartment(kittenId, departmentId);
    }
}
