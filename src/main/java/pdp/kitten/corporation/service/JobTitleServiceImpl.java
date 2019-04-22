package pdp.kitten.corporation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.kitten.corporation.domain.JobTitle;
import pdp.kitten.corporation.repository.JobTitleRepository;

import java.util.List;

@Service
public class JobTitleServiceImpl implements JobTitleService {

    private JobTitleRepository jobTitleRepository;

    @Autowired
    public JobTitleServiceImpl(JobTitleRepository jobTitleRepository) {
        this.jobTitleRepository = jobTitleRepository;
    }

    @Override
    public List<JobTitle> getAll() {
        return jobTitleRepository.findAll();
    }

    @Override
    public JobTitle get(String id) {
        return jobTitleRepository.find(id);
    }

    @Override
    public void delete(String id) {
        jobTitleRepository.delete(id);
    }

    @Override
    public void save(JobTitle jobTitle) {
        jobTitleRepository.save(jobTitle);
    }
}
