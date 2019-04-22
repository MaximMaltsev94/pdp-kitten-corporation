package pdp.kitten.corporation.repository.converter;

import org.bson.Document;
import org.springframework.stereotype.Component;
import pdp.kitten.corporation.domain.Department;

import static pdp.kitten.corporation.repository.util.DocumentUtils.putIfNotNull;
import static pdp.kitten.corporation.repository.util.DocumentUtils.putObjectId;

@Component
public class DepartmentToDocumentConverter implements GenericConverter<Department, Document> {
    @Override
    public Document convert(Department department) {
        Document result = new Document();
        putObjectId(result, "_id", department.getId());
        putIfNotNull(result, "name", department.getName());
        putIfNotNull(result, "maxKittenCount", department.getMaxKittenCount());
        return result;
    }
}
