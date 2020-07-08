package pdp.kitten.corporation.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.UpdateOptions;
import org.apache.commons.collections4.CollectionUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pdp.kitten.corporation.domain.Kitten;
import pdp.kitten.corporation.repository.converter.KittenAggregateConverter;
import pdp.kitten.corporation.repository.converter.KittenToDocumentConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static pdp.kitten.corporation.repository.util.KittenCorporationCollections.DEPARTMENT_COLLECTION;
import static pdp.kitten.corporation.repository.util.KittenCorporationCollections.JOB_TITLE_COLLECTION;

//@Repository
public class KittenRepositoryImpl implements KittenRepository {

    private MongoCollection<Document> kittenMongoCollection;

    private KittenAggregateConverter kittenConverter;

    private KittenToDocumentConverter kittenToDocumentConverter;

    @Autowired
    public KittenRepositoryImpl(@Qualifier("kittenMongoCollection") MongoCollection<Document> kittenMongoCollection,
                                KittenAggregateConverter kittenConverter,
                                KittenToDocumentConverter kittenToDocumentConverter) {
        this.kittenMongoCollection = kittenMongoCollection;
        this.kittenConverter = kittenConverter;
        this.kittenToDocumentConverter = kittenToDocumentConverter;
    }

    @Override
    public List<Kitten> findAll() {
        return kittenConverter.convertMany(kittenMongoCollection.aggregate(findKittensAggregate()));
    }

    @Override
    public Kitten find(String id) {
        List<Bson> kittensAggregate = findKittensAggregate();
        Bson matchIdAggregate = Aggregates.match(eq("_id", new ObjectId(id)));
        kittensAggregate.add(0, matchIdAggregate);
        return kittenConverter.convertOne(kittenMongoCollection.aggregate(kittensAggregate));
    }

    private List<Bson> findKittensAggregate() {
        List<Bson> aggregates = new ArrayList<>();
        aggregates.add(Aggregates.lookup(DEPARTMENT_COLLECTION.getCollectionName(), "department", "_id", "department"));
        aggregates.add(Aggregates.lookup(JOB_TITLE_COLLECTION.getCollectionName(), "jobTitle", "_id", "jobTitle"));
        return aggregates;
    }

    @Override
    public Kitten save(Kitten kitten) {
        if (kitten == null) {
            return null;
        }
        Document kittenBson = kittenToDocumentConverter.convert(kitten);
        if (kitten.getId() == null) {
            kittenMongoCollection.insertOne(kittenBson);
        } else {
            kittenMongoCollection.replaceOne(eq("_id", new ObjectId(kitten.getId())), kittenBson);
        }
        kitten.setId(kittenBson.getObjectId("_id").toHexString());
        return kitten;
    }

    @Override
    public void delete(String id) {
        kittenMongoCollection.deleteOne(eq("_id", new ObjectId(id)));
    }

    @Override
    public void setKittenDepartment(String kittenId, String departmentId) {
        if (kittenId == null || departmentId == null) {
            return;
        }
        kittenMongoCollection.updateOne(new Document("_id", new ObjectId(kittenId)), new Document("$set", new Document("department", new ObjectId(departmentId))));
    }
}
