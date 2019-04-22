package pdp.kitten.corporation.repository.converter;

import org.apache.commons.collections4.CollectionUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pdp.kitten.corporation.domain.Department;
import pdp.kitten.corporation.domain.JobTitle;
import pdp.kitten.corporation.domain.Kitten;

import java.util.List;

@Component
public class KittenAggregateConverter implements GenericConverter<Document, Kitten> {

    private JobTitleConverter jobTitleConverter;
    private DepartmentAggregateConverter departmentConverter;

    @Autowired
    public KittenAggregateConverter(JobTitleConverter jobTitleConverter, DepartmentAggregateConverter departmentConverter) {
        this.jobTitleConverter = jobTitleConverter;
        this.departmentConverter = departmentConverter;
    }

    @Override
    public Kitten convert(Document document) {
        if(document == null) {
            return null;
        }

        if(!document.containsKey("_id")) {
            return null;
        }

        return new Kitten(
                document.getObjectId("_id").toHexString(),
                document.getString("name"),
                document.getInteger("age"),
                getDepartment(document),
                getJobTitle(document)
        );
    }

    private JobTitle getJobTitle(Document document) {
        Object jobTitle = document.get("jobTitle");
        if(jobTitle instanceof ObjectId) {
            return null;
        }
        if(jobTitle instanceof List) {
            List<Document> jobTitleList = document.getList("jobTitle", Document.class);
            if(CollectionUtils.isEmpty(jobTitleList)) {
                return null;
            }
            return jobTitleConverter.convert(jobTitleList.get(0));
        }
        return null;
    }

    private Department getDepartment(Document document) {
        Object department = document.get("department");
        if(department instanceof ObjectId) {
            return null;
        }
        if(department instanceof List) {
            List<Document> departmentList = document.getList("department", Document.class);
            if(CollectionUtils.isEmpty(departmentList)) {
                return null;
            }
            return departmentConverter.convert(departmentList.get(0));
        }
        return null;
    }

}
