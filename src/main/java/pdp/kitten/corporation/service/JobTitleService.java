package pdp.kitten.corporation.service;

import pdp.kitten.corporation.domain.JobTitle;

import java.util.List;

public interface JobTitleService {

    List<JobTitle> getAll();

    JobTitle get(String id);

    void delete(String id);

    void save(JobTitle kitten);
}
