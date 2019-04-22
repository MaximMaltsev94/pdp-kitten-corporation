package pdp.kitten.corporation.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pdp.kitten.corporation.domain.JobTitle;

@Component("jobTitleControllerConverter")
public class JobTitleConverter implements Converter<String, JobTitle> {
    @Override
    public JobTitle convert(String id) {
        return new JobTitle(id, null);
    }
}
