package pdp.kitten.corporation.repository;


import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.UnwindOptions;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pdp.kitten.corporation.domain.Department;
import pdp.kitten.corporation.repository.converter.DepartmentAggregateConverter;
import pdp.kitten.corporation.repository.converter.DepartmentToDocumentConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static pdp.kitten.corporation.repository.util.KittenCorporationCollections.JOB_TITLE_COLLECTION;
import static pdp.kitten.corporation.repository.util.KittenCorporationCollections.KITTEN_COLLECTION;

//@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private MongoCollection<Document> departmentCollection;

    private DepartmentAggregateConverter departmentConverter;
    private DepartmentToDocumentConverter departmentToDocumentConverter;

    @Autowired
    public DepartmentRepositoryImpl(@Qualifier("departmentMongoCollection") MongoCollection<Document> departmentCollection,
                                    DepartmentAggregateConverter departmentConverter,
                                    DepartmentToDocumentConverter departmentToDocumentConverter) {
        this.departmentCollection = departmentCollection;
        this.departmentConverter = departmentConverter;
        this.departmentToDocumentConverter = departmentToDocumentConverter;
    }

    @Override
    public List<Department> findAll() {
        AggregateIterable<Document> aggregate = departmentCollection.aggregate(findDepartmentsAggregate());
        return departmentConverter.convertMany(aggregate);
    }

    @Override
    public Department find(String id) {
        List<Bson> departmentsAggregate = findDepartmentsAggregate();
        Bson matchDepartmentId = Aggregates.match(eq("_id", new ObjectId(id)));
        departmentsAggregate.add(0, matchDepartmentId);
        return departmentConverter.convertOne(departmentCollection.aggregate(departmentsAggregate));
    }

    private List<Bson> findDepartmentsAggregate() {
        List<Bson> aggregates = new ArrayList<>();
        aggregates.add(Aggregates.lookup(KITTEN_COLLECTION.getCollectionName(), "_id", "department", "kittens"));
        aggregates.add(Aggregates.unwind("$kittens", new UnwindOptions().preserveNullAndEmptyArrays(true)));
        aggregates.add(Aggregates.lookup(JOB_TITLE_COLLECTION.getCollectionName(), "kittens.jobTitle", "_id", "kittens.jobTitle"));
        aggregates.add(Aggregates.group(
                "$_id",
                new BsonField("name", new Document("$first", "$name")),
                new BsonField("maxKittenCount", new Document("$first", "$maxKittenCount")),
                new BsonField("kittens", new Document("$push", "$kittens"))));
        return aggregates;
    }

    @Override
    public Department save(Department department) {
        if(department == null) {
            return null;
        }
        Document departmentBson = departmentToDocumentConverter.convert(department);
        if(department.getId() == null) {
            departmentCollection.insertOne(departmentBson);
        } else {
            departmentCollection.updateOne(eq("_id", new ObjectId(department.getId())), departmentBson);
        }
        department.setId(departmentBson.getObjectId("_id").toHexString());
        return department;
    }

    @Override
    public void delete(String id) {
        departmentCollection.deleteOne(eq("_id", new ObjectId(id)));
    }
}
