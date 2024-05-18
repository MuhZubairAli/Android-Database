package pk.gov.pbs.database_project;

public class TestModel {
    public String block;
    public String name;
    public int age;
    public long unix_age;

    public TestModel(String block, String name, int age, long unix_age) {
        this.block = block;
        this.name = name;
        this.age = age;
        this.unix_age = unix_age;
    }
}
