package test.test.tp3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

import test.test.tp3.MainActivity;
import test.test.tp3.Student;

public class StudentHelper extends SQLiteOpenHelper implements Serializable {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Students.db";

    public StudentHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StudentReaderContract.SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(StudentReaderContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void AddStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(StudentReaderContract.StudentEntry.COLUMN_1, student.getLastname());
        values.put(StudentReaderContract.StudentEntry.COLUMN_2, student.getFirstname());
        values.put(StudentReaderContract.StudentEntry.COLUMN_3, student.getSexe());
        values.put(StudentReaderContract.StudentEntry.COLUMN_4, student.getEmail());
        values.put(StudentReaderContract.StudentEntry.COLUMN_5, MainActivity.DATE_FORMAT.format(student.getBirthday()));
        values.put(StudentReaderContract.StudentEntry.COLUMN_6, student.getGroup());
        values.put(StudentReaderContract.StudentEntry.COLUMN_7, student.isRedoublant());

        long rowId = db.insert(StudentReaderContract.StudentEntry.TABLE_NAME, null, values);

        student.setId(rowId);
    }

    public void DeleteStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = StudentReaderContract.StudentEntry._ID + " LIKE ?";

        String[] selectionArgs = { String.valueOf(student.getId()) };

        int deletedRows = db.delete(StudentReaderContract.StudentEntry.TABLE_NAME, selection, selectionArgs);
    }

}
