package hu.elte.melvin.efoodsystem.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public abstract class OrderDao {

    @Insert
    public abstract void insertOrder(Order order);

    @Insert
    public abstract void insertOrderLists(List<OrderList> orderLists);

    @Query("SELECT * from `order` WHERE id == :orderID ORDER BY timeOfOrder ASC")
    public abstract Order getOrder(final int orderID);

    @Query("SELECT * from orderlist WHERE orderID == :orderID")
    public abstract LiveData<List<OrderList>> getOrderListByCustomer(final int orderID);

    @Query("SELECT * from `order` WHERE customerID == :customerID ORDER BY timeOfOrder ASC")
    public abstract LiveData<List<Order>> getAllOrdersForCustomer(final int customerID);

    public void insertOrderWithOrderList(Order order) {
        List<OrderList> orderLists = order.getOrderLists();
        for (int i = 0; i < orderLists.size(); i++) {
            orderLists.get(i).setOrderID(order.getId());
        }
        insertOrderLists(orderLists);
        insertOrder(order);
    }

    public Order getOrderWithOrderList(int id) {
        Order order = getOrder(id);
        LiveData<List<OrderList>> orderLists = getOrderListByCustomer(id);
        order.setOrderLists(orderLists.getValue());
        return order;
    }
//
//    @Query("SELECT * from food WHERE foodname == :foodName")
//    abstract Food getFoodByName(String foodName);


    /*
        @Query("DELETE FROM food where id == :foodID")
        void delete(String foodID);
        */

/*
    @Query("DELETE FROM `order`")
    public abstract void deleteAllFoods();

    @Delete
    public abstract void deleteOrders(Order... orders);

    @Update
    public abstract void updateOrders(Order... orders);
*/

}
