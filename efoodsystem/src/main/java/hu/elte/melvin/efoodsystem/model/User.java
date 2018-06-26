package hu.elte.melvin.efoodsystem.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "user")
public class User {
//    [User] id, username, role, fullname, companyID, password

    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "word")
    private int id;
//    @NonNull
    private String username; //for customer = email
//    @NonNull
    private String role; //Customer, Manager or Admin and Root
    private String companyID;
    private String password;

    public User(int id, @NonNull String username, @NonNull String role, String companyID, String password) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.companyID = companyID;
        this.password = password;
    }

    public User(){

    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getRole() {
        return role;
    }

    public String getCompanyID() {
        return companyID;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setRole(@NonNull String role) {
        this.role = role;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }
}
