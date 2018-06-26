package hu.elte.melvin.efoodsystem.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface FoodDao {
    @Insert
    void insertFoods(Food... foods);

    @Query("DELETE FROM food")
    void deleteAllFoods();
/*
    @Query("DELETE FROM food where id == :foodID")
    void delete(String foodID);*/

    @Delete
    void deleteFoods(Food... foods);

    @Update
    void updateFoods(Food... foods);

    @Query("SELECT * from food")
    Food[] getAllFoods();

}