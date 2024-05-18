package pk.gov.pbs.database_project;

import pk.gov.pbs.database.annotations.PrimaryKey;
import pk.gov.pbs.database.annotations.SqlPrimaryKey;
import pk.gov.pbs.database.annotations.Table;
import pk.gov.pbs.database.annotations.Unique;

@Table(version = 5)
public class RosterModel {
    @SqlPrimaryKey(autogenerate = true)
    @PrimaryKey(autogenerate = true)
    public Integer id;

    public String block;

    @Unique(index = "name_gender_key")
    public String fullName;
    @Unique(index = "name_gender_key")
    public Integer gender;
    @Unique
    public Integer age;
    @Unique
    public Integer relation;

    public RosterModel() {
    }

    public RosterModel(String block, String fullName, Integer age, Integer relation, Integer gender) {
        this.block = block;
        this.fullName = fullName;
        this.age = age;
        this.relation = relation;
        this.gender = gender;
    }
}
