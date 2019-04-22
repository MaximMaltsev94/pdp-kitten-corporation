package pdp.kitten.corporation.domain;

import java.util.List;

public class Department {
    private String id;
    private String name;
    private Integer maxKittenCount;
    private List<Kitten> kittens;

    public Department() {
    }

    public Department(String id, String name, Integer maxKittenCount, List<Kitten> kittens) {
        this.id = id;
        this.name = name;
        this.maxKittenCount = maxKittenCount;
        this.kittens = kittens;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKittenCount() {
        return kittens == null ? 0 : kittens.size();
    }

    public Integer getMaxKittenCount() {
        return maxKittenCount;
    }

    public void setMaxKittenCount(Integer maxKittenCount) {
        this.maxKittenCount = maxKittenCount;
    }

    public List<Kitten> getKittens() {
        return kittens;
    }

    public void setKittens(List<Kitten> kittens) {
        this.kittens = kittens;
    }
}
