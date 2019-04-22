package pdp.kitten.corporation.repository.util;

public enum KittenCorporationCollections {

    KITTEN_COLLECTION("kittens"),
    DEPARTMENT_COLLECTION("departments"),
    JOB_TITLE_COLLECTION("job-titles");

    private String collectionName;

    KittenCorporationCollections(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionName() {
        return collectionName;
    }
}
