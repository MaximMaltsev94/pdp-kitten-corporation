package pdp.kitten.corporation.repository.converter;

import org.bson.Document;
import org.springframework.stereotype.Component;
import pdp.kitten.corporation.domain.JobTitle;

import static pdp.kitten.corporation.repository.util.DocumentUtils.putIfNotNull;
import static pdp.kitten.corporation.repository.util.DocumentUtils.putObjectId;

@Component
public class JobTitleToDocumentConverter implements GenericConverter<JobTitle, Document> {
    @Override
    public Document convert(JobTitle jobTitle) {
        Document result = new Document();
        putObjectId(result, "_id", jobTitle.getId());
        putIfNotNull(result, "name", jobTitle.getName());
        return result;
    }
}
