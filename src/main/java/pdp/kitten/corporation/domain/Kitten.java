package pdp.kitten.corporation.domain;

public class Kitten {
    private String id;
    private String name;
    private Integer age;
    private Department department;
    private JobTitle jobTitle;

    public Kitten() {
    }

    public Kitten(String id, String name, Integer age, Department department, JobTitle jobTitle) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.jobTitle = jobTitle;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }
}
