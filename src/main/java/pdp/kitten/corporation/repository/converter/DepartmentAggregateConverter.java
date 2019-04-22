package pdp.kitten.corporation.repository.converter;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pdp.kitten.corporation.domain.Department;

@Component
public class DepartmentAggregateConverter implements GenericConverter<Document, Department> {

    private KittenAggregateConverter kittenConverter;

    @Autowired
    public void setKittenConverter(KittenAggregateConverter kittenConverter) {
        this.kittenConverter = kittenConverter;
    }

    @Override
    public Department convert(Document document) {
        if(document == null) {
            return null;
        }

        return new Department(
                document.getObjectId("_id").toHexString(),
                document.getString("name"),
                document.getInteger("maxKittenCount"),
                kittenConverter.convertMany(document.getList("kittens", Document.class)));
    }


}
