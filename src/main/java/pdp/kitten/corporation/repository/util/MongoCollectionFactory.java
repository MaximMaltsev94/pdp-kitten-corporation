package pdp.kitten.corporation.repository.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoCollectionFactory {
    private String databaseName;

    private MongoClient mongoClient;

    public MongoCollectionFactory(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public MongoCollection<Document> getCollection(KittenCorporationCollections collection) {
        return mongoClient.getDatabase(databaseName).getCollection(collection.getCollectionName());
    }
}
