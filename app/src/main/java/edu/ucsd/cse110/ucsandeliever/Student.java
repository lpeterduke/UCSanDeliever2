package edu.ucsd.cse110.ucsandeliever;

import java.io.Serializable;

/**
 * Created by jake on 2016/10/19.
 */

public class Student implements Serializable {

    private String uid;
    private String name;
    private String studentId;
    private String emai;
    private String password;
    private String balance;

    // 0 for default, 1 for selected by requester, 2 for rejected by requester
    private Boolean runnerStatusIndicator;
    private Boolean alreadyPick;



    private Boolean requesting;
    // if requesting is true,when login screen will lock


    public Boolean getRequesting() {
        return requesting;
    }

    public void setRequesting(Boolean requesting) {

        this.requesting = requesting;
    }

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

    public void setuid(String uid){this.uid=uid;}
    public String getuid(){return this.uid;}
    public void setBalance(String balance){this.balance= balance;}
    public String getBalance(){
        return balance;
    }

    public Boolean getRunnerStatusIndicator(){return this.runnerStatusIndicator;}
    public void setRunnerStatusIndicator(Boolean newStatus){this.runnerStatusIndicator = newStatus;}

    public Boolean getAlreadyPick() {
        return alreadyPick;
    }

    public void setAlreadyPick(Boolean alreadyPick) {

        this.alreadyPick = alreadyPick;
    }

    public Student(){

    }
    public Student(String uid, String name, String emai){
        this.uid = uid;
        this.name = name;
        this.balance= "2000";
        this.runnerStatusIndicator = false;
        this.alreadyPick = false;
    }
}

