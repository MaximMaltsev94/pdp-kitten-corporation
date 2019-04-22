package pdp.kitten.corporation.repository.converter;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import pdp.kitten.corporation.domain.Department;
import pdp.kitten.corporation.domain.JobTitle;
import pdp.kitten.corporation.domain.Kitten;

import static pdp.kitten.corporation.repository.util.DocumentUtils.putIfNotNull;
import static pdp.kitten.corporation.repository.util.DocumentUtils.putObjectId;

@Component
public class KittenToDocumentConverter implements GenericConverter<Kitten, Document> {
    @Override
    public Document convert(Kitten kitten) {
        Document result = new Document();
        putObjectId(result, "_id", kitten.getId());
        putIfNotNull(result, "name", kitten.getName());
        putIfNotNull(result, "age", kitten.getAge());
        putIfNotNull(result, "department", getDepartmentId(kitten.getDepartment()));
        putIfNotNull(result, "jobTitle", getJobTitleId(kitten.getJobTitle()));
        return result;
    }

    private ObjectId getDepartmentId(Department department) {
        if(department == null || StringUtils.isBlank(department.getId())) {
            return null;
        }
        return new ObjectId(department.getId());
    }

    private ObjectId getJobTitleId(JobTitle jobTitle) {
        if(jobTitle == null || StringUtils.isBlank(jobTitle.getId())) {
            return null;
        }
        return new ObjectId(jobTitle.getId());
    }
}
