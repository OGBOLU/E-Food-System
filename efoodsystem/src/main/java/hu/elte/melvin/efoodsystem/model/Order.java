package hu.elte.melvin.efoodsystem.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(tableName = "order")
        /*,foreignKeys = @ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "customerID",
                        onDelete = CASCADE))*/
public class Order {
//    [Order] id, customerID, price, discount, amountPaid,
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int customerID;
    private double totalprice;
    @NonNull
    private Date timeOfOrder;
    private double discount;
    private double amountpaid;

    @Ignore
    private List<OrderList> orderLists;
    @Ignore
    private User customer;

    public Order(final int id, final int customerID, double totalprice,
                                    @NonNull Date timeOfOrder, double discount, double amountpaid) {
        this.id = id;
        this.customerID = customerID;
        this.totalprice = totalprice;
        this.discount = discount;
        this.amountpaid = amountpaid;
        this.timeOfOrder = timeOfOrder;
        orderLists = new ArrayList<>();
        customer = new User();
    }

    public Order(){
        orderLists = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public double getDiscount() {
        return discount;
    }

    public double getAmountpaid() {
        return amountpaid;
    }

    @NonNull public Date getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public void setTimeOfOrder(@NonNull Date timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setAmountpaid(double amountpaid) {
        this.amountpaid = amountpaid;
    }

    public List<OrderList> getOrderLists() {
        return orderLists;
    }

    public void setOrderLists(List<OrderList> orderLists) {
        this.orderLists = orderLists;
    }

    public void addToOrderList(OrderList orderList){
        this.orderLists.add(orderList);
    }

    public void removeFromOrderList(int foodID){
        this.orderLists.removeIf(o->o.getFoodID() == foodID);
    }

    public void setCustomer(User customer) {
        this.customer = customer;
        this.customerID = customer.getId();
    }

    public User getCustomer() {
        return customer;
    }

    public void calculateTotalCost() {
        double total = 0.0;
        for (OrderList item : orderLists) {
            total += item.getQuantity() * item.getFood().getPrice();

        }
        setTotalprice(total);
    }
}
