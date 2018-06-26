package hu.elte.melvin.efoodsystem.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "orderlist")
public class OrderList {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int orderID;
    private int foodID;
    private int quantity = 1;

    @Ignore
    private Food food;

    public OrderList(){}
    public OrderList(int id, int orderID, int foodID, int quantity) {
        this.id = id;
        this.orderID = orderID;
        this.foodID = foodID;
        this.quantity = quantity;
        food = new Food();
    }

    public int getId() {
        return id;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
        this.food.setId(foodID);
    }

    public void setFood(Food food) {
        this.foodID = food.getId();
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
