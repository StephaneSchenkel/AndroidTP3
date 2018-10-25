package test.test.tp3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test.test.tp3.db.StudentHelper;
import test.test.tp3.db.StudentReaderContract;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private ListStudentFragment listStudentFragment;

    static List<Student> List = new ArrayList<>();
    static Student SelectedStudent;
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final int ADD_STUDENT = 1;

    private StudentHelper helper;

    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new StudentHelper(getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();

        try {
            InitializeHelper();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String value = prefs.getString("affichage", "np");
        prefs.registerOnSharedPreferenceChangeListener(this);

        listStudentFragment = ListStudentFragment.newInstance(helper, value);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.listFragment, listStudentFragment);
        transaction.add(R.id.infoFragment, new StudentInfoFragment());
        transaction.commit();

        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {int id = item.getItemId();

        switch(id){
            case R.id.action_add:
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivityForResult(intent, ADD_STUDENT);
                return true;
            case R.id.action_delete:
                if(SelectedStudent != null)
                    listStudentFragment.DeleteCurrentStudent();
                else
                    Toast.makeText(MainActivity.this, "Select a student before clicking on delete", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Intent intentpreference = new Intent(MainActivity.this, Preferences.class);
                startActivity(intentpreference);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch(requestCode){
            case ADD_STUDENT:
                if(resultCode == Activity.RESULT_OK && data.hasExtra("Student"))
                {
                    listStudentFragment.AddStudent((Student)data.getSerializableExtra("Student"));
                    Toast.makeText(MainActivity.this, "Student added to the list", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void InitializeHelper() throws ParseException {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + StudentReaderContract.StudentEntry.TABLE_NAME, null);
        if(cursor.moveToFirst()){
            List.add(Student.GetFromCursor(cursor));
            while(cursor.moveToNext()){
                List.add(Student.GetFromCursor(cursor));
            }
        }
        cursor.close();
        db.close();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        String value = sharedPreferences.getString("affichage", "np");
        listStudentFragment.UpdatePref(value);
    }
}
