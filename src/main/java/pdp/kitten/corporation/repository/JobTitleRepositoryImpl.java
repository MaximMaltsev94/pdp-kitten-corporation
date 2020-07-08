package pdp.kitten.corporation.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pdp.kitten.corporation.domain.JobTitle;
import pdp.kitten.corporation.repository.converter.JobTitleConverter;
import pdp.kitten.corporation.repository.converter.JobTitleToDocumentConverter;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

//@Repository
public class JobTitleRepositoryImpl implements JobTitleRepository {

    private MongoCollection<Document> jobTitleMongoCollection;

    private JobTitleConverter jobTitleConverter;

    private JobTitleToDocumentConverter jobTitleToDocumentConverter;

    @Autowired
    public JobTitleRepositoryImpl(@Qualifier("jobTitleMongoCollection") MongoCollection<Document> jobTitleMongoCollection,
                                  JobTitleConverter jobTitleConverter,
                                  JobTitleToDocumentConverter jobTitleToDocumentConverter) {
        this.jobTitleMongoCollection = jobTitleMongoCollection;
        this.jobTitleConverter = jobTitleConverter;
        this.jobTitleToDocumentConverter = jobTitleToDocumentConverter;
    }

    @Override
    public List<JobTitle> findAll() {
        return jobTitleConverter.convertMany(jobTitleMongoCollection.find());
    }

    @Override
    public JobTitle find(String id) {
        return jobTitleConverter.convertOne(jobTitleMongoCollection.find(eq("_id", new ObjectId(id))));
    }

    @Override
    public JobTitle save(JobTitle jobTitle) {
        if (jobTitle == null) {
            return null;
        }
        Document jobTitleBson = jobTitleToDocumentConverter.convert(jobTitle);
        if (jobTitle.getId() == null) {
            jobTitleMongoCollection.insertOne(jobTitleBson);
        } else {
            jobTitleMongoCollection.updateOne(eq("_id", new ObjectId(jobTitle.getId())), jobTitleBson);
        }
        jobTitle.setId(jobTitleBson.getObjectId("_id").toHexString());
        return jobTitle;
    }

    @Override
    public void delete(String id) {
        jobTitleMongoCollection.deleteOne(eq("_id", new ObjectId(id)));
    }
}
