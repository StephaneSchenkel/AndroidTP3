package test.test.tp3;

import android.database.Cursor;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import test.test.tp3.db.StudentReaderContract;

public class Student implements Serializable {

    private long id;
    private String lastname;
    private String firstname;
    private String sexe;
    private String email;
    private Date birthday;
    private String group;
    private boolean redoublant;

    public Student(){

    }

    public Student(String lastname, String firstname, String sexe, String email, Date birthday, String group, boolean redoublant) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.sexe = sexe;
        this.email = email;
        this.birthday = birthday;
        this.group = group;
        this.redoublant = redoublant;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public boolean isRedoublant() {
        return redoublant;
    }

    public void setRedoublant(boolean redoublant) {
        this.redoublant = redoublant;
    }

    public static Student GetFromCursor(Cursor cursor) throws ParseException {
        Student student = new Student();
        student.id = cursor.getInt(cursor.getColumnIndex(StudentReaderContract.StudentEntry._ID));
        student.lastname = cursor.getString(cursor.getColumnIndex(StudentReaderContract.StudentEntry.COLUMN_1));
        student.firstname = cursor.getString(cursor.getColumnIndex(StudentReaderContract.StudentEntry.COLUMN_2));
        student.birthday = MainActivity.DATE_FORMAT.parse(cursor.getString(cursor.getColumnIndex(StudentReaderContract.StudentEntry.COLUMN_5)));
        student.email = cursor.getString(cursor.getColumnIndex(StudentReaderContract.StudentEntry.COLUMN_4));
        student.group = cursor.getString(cursor.getColumnIndex(StudentReaderContract.StudentEntry.COLUMN_6));
        student.sexe = cursor.getString(cursor.getColumnIndex(StudentReaderContract.StudentEntry.COLUMN_3));
        student.redoublant = cursor.getInt(cursor.getColumnIndex(StudentReaderContract.StudentEntry.COLUMN_7)) > 0;
        return student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}