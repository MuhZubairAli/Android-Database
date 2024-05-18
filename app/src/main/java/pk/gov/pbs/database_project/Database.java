package pk.gov.pbs.database_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import pk.gov.pbs.database.ModelBasedDatabaseHelper;
import pk.gov.pbs.database.exceptions.UnsupportedDataType;

public class Database extends ModelBasedDatabaseHelper {
    public static final String dbName = "test.sqlite";
    public static final Class<?>[] models = new Class[]{
            TestModel.class,
            RosterModel.class,
            TestModelMultiPK.class
    };

    public Database(Context context) {
        super(context, dbName, models);
    }
}
