package pdp.kitten.corporation.repository.converter;

import org.bson.Document;
import org.springframework.stereotype.Component;
import pdp.kitten.corporation.domain.JobTitle;

@Component
public class JobTitleConverter implements GenericConverter<Document, JobTitle> {

    @Override
    public JobTitle convert(Document document) {
        if(document == null) {
            return null;
        }

        return new JobTitle(
                document.getObjectId("_id").toHexString(),
                document.getString("name")
        );
    }
}
