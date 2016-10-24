package edu.ucsd.cse110.ucsandeliever;

/**
 * Created by jake on 2016/10/19.
 */

public class Student {

    private String name;
    private String studentId;
    private String emai;
    private String password;


    // if requesting is true,when login screen will lock
    private boolean requesting;

    public boolean getRequestingSatus(){ return requesting;  }
    public void setRequestingStatus(boolean boo){ requesting=boo;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail2() {
        return emai;
    }

    public void setEmail2(String emai1) {
        this.emai = emai1;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public Student(){

    }
}

