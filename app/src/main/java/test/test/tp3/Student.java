package test.test.tp3;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

    private String lastname;
    private String firstname;
    private String sexe;
    private String email;
    private Date birthday;
    private String group;
    private boolean redoublant;

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
}