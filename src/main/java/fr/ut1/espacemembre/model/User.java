package fr.ut1.espacemembre.model;


import java.sql.Date;

public class User {

    private int id;
    private String fistName;
    private String lastName;
    private Date dateofBirth;
    private String email;
    private String password;

    public User() {}

    public User(String fistName, String lastName, Date dateofBirth, String email, String password) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.dateofBirth = dateofBirth;
        this.email = email;
        this.password = password;
    }

    public User(int id, String fistName, String lastName, Date dateofBirth, String email, String password) {
        this.id = id;
        this.fistName = fistName;
        this.lastName = lastName;
        this.dateofBirth = dateofBirth;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(Date dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
