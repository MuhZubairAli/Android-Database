package pk.gov.pbs.database_project;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

import pk.gov.pbs.utils.StaticUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Database dbInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbInstance = new Database(this);
        //seed(); //run only at first run
        test();
    }

    private void seed(){
        TestModel obj1 = new TestModel("123", "Tester 1", 12, 1234567);
        dbInstance.insert(obj1);
        dbInstance.insert(new RosterModel("123", "Ahmed", 30, 1, 1));
        dbInstance.insert(new RosterModel("456", "Ali", 29, 2, 2));
        dbInstance.insert(new TestModelMultiPK(1, "abc", 123, "This is test string"));
        dbInstance.insert(new TestModelMultiPK(1, "def", 123, "This is test string 2"));
        dbInstance.insert(new TestModelMultiPK(1, "def", 456, "This is test string 2"));
    }
    private void test(){
        List<Map<String, String>> res = dbInstance.queryRowsAsMap("SELECT block, age, fullName FROM RosterModel");
        List<ValueStore[]> resArr = dbInstance.queryRowsWith("SELECT block, age, fullName FROM RosterModel", (cursor, columnIndex) -> new ValueStore(cursor.getString(columnIndex)));

        List<String[]> joinedRes = dbInstance.queryRowsAsList("select x.*, y.* from RosterModel x join TestModel y on x.block = y.block");
        List<Map<String, String>> joinedMap = dbInstance.queryRowsAsMap("select x.*, y.* from RosterModel x join TestModel y on x.block = y.block");
        List<Map<String, ValueStore>> joinedMapWith = dbInstance.queryRowsAsMapWith("select x.*, y.* from RosterModel x join TestModel y on x.block = y.block", (c, i) -> new ValueStore(c.getString(i)));

        List<RosterModel> rosterModels = dbInstance.queryRawSql(RosterModel.class, "SELECT fullName FROM <table>");
        Long count = dbInstance.getCount(RosterModel.class);
        String name = dbInstance.queryString("SELECT fullName FROM RosterModel where id=1");
        Integer age = dbInstance.queryInteger("SELECT age FROM RosterModel where id=1");
        int tage = dbInstance.queryWith("select max(age) from TestModel", Cursor::getInt);
        long ts = dbInstance.queryWith("select min(unix_age) from TestModel", Cursor::getLong);

        List<TestModelMultiPK> testModelMultiPKS = dbInstance.query(TestModelMultiPK.class);
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(testModelMultiPKS);
        Log.d(TAG, "test: " + json);

        int j = joinedRes.size();
        int i = res.size();
    }

}