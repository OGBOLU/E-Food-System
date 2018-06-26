package hu.elte.melvin.efoodsystem.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUsers(User... users);

    @Query("DELETE FROM user")
    void deleteAllUserss();
/*
    @Query("DELETE FROM food where id == :foodID")
    void delete(String foodID);*/

    @Delete
    void deleteUsers(User... users);

    @Update
    void updateFoods(Food... foods);

    @Query("SELECT * from user WHERE username == :username and password == :password")
    User getUser(final String username, final String password);

    @Query("SELECT * from user WHERE username != 'root'")
    LiveData<List<User>> getAllUsers();

}

