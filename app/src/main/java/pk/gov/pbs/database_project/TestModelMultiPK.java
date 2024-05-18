package pk.gov.pbs.database_project;

import pk.gov.pbs.database.annotations.PrimaryKey;
import pk.gov.pbs.database.annotations.Table;

@Table(version = 4)
public class TestModelMultiPK {
    @PrimaryKey(autogenerate = false)
    public int a;
    @PrimaryKey(autogenerate = false)
    public String b;
    @PrimaryKey(autogenerate = false)
    public int c;
    public String d;

    public TestModelMultiPK(){}

    public TestModelMultiPK(int a, String b, int c, String d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
}
