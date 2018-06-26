package hu.elte.melvin.efoodsystem;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import hu.elte.melvin.efoodsystem.model.Food;
import hu.elte.melvin.efoodsystem.model.Order;
import hu.elte.melvin.efoodsystem.model.OrderList;
import hu.elte.melvin.efoodsystem.model.User;

import java.util.Calendar;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private AppDataRepository appDataRepo;

    private MutableLiveData<Enums.state> mState;
    private MutableLiveData<Boolean> isloggedIn;
    private boolean loggedIn = false;
    private User currentUser;
    private MutableLiveData<Order> foodOrder;
    private List<Food> allFoods;

    public MainViewModel(Application application){
        super(application);
        appDataRepo = AppDataRepository.getInstance(application);//((AppUtil) application).getRepository();
        appDataRepo.getAllFoods(); //TODO check if UI thread?
        mState = new MutableLiveData<>();
        mState.setValue(Enums.state.Base);
        isloggedIn = new MutableLiveData<>();
        isloggedIn.setValue(loggedIn);
    }

    public boolean logUserIn(String username, String password){
        User user = appDataRepo.getUser(username, password);
        if (user != null){
            setCurrentUser(user);
            return true;
        }else { //register it
            if (currentUser == null) {
                //then it's a customer
                User customer = new User();
                customer.setPassword(password);
                customer.setUsername(username);
                customer.setRole("customer");
                appDataRepo.insert(customer);
                setCurrentUser(customer);
                return true;
            }
        }
        return false;
    }

    public void logUserOut(){
        setCurrentUser(null);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        if(currentUser == null){
            setIsloggedIn(false);
        }else {
            setIsloggedIn(true);
        }
    }

    public void addFoodForOrder(int foodID, String foodName, double foodprice, String foodDescr, String foodURL){//TODO ?!
        if (foodOrder == null){
            foodOrder = new MutableLiveData<>();
            Order order = new Order();
            order.setCustomer(currentUser);
            order.setTimeOfOrder(Calendar.getInstance().getTime());
            foodOrder.setValue(order);
        }
        OrderList orderList = new OrderList();
        Food food = new Food();
        food.setId(foodID);
        food.setImageURL(foodURL);
        food.setDescription(foodDescr);
        food.setPrice(foodprice);
        food.setFoodname(foodName);
        orderList.setFood(food);
        foodOrder.getValue().addToOrderList(orderList);

        //TODO - if Order is null create Order ; add orderList to Order
    }

    public void removeFoodFromOrder(int foodID){//TODO ?!
        if ((foodOrder != null) && (foodOrder.getValue().getOrderLists().size() != 0)){
            foodOrder.getValue().removeFromOrderList(foodID);
        }
    }

    public void clearCurrentOrder() {
        foodOrder = null;
    }

    public boolean isOrderEmpty(){
        if (foodOrder == null || foodOrder.getValue().getOrderLists().size() == 0){
            return true;
        }
        return false;
    }

    public MutableLiveData<Order> getFoodOrder() {
        if (foodOrder == null){
            foodOrder = new MutableLiveData<>();
//            foodOrder.getValue().setCustomer(currentUser);
        }
        foodOrder.getValue().calculateTotalCost();
        return foodOrder;
    }

    public MutableLiveData<Boolean> isIsloggedIn() {
        isloggedIn.setValue(loggedIn);
        return isloggedIn;
    }

    private void setIsloggedIn(boolean isloggedIn) {
        loggedIn = isloggedIn;
    }

    public List<Food> getAllFoods(){
        return  allFoods;
    }

    public void switchToLogIn() {
        try {
            if(!isIsloggedIn().getValue()){
                mState.setValue(Enums.state.Login);
            }else {
                //TODO - inform using snackBar
            }

        } catch (Exception e) {
            Log.e(".LogIn", e.toString());
            e.printStackTrace();
        }
    }

    public void switchToBase() {
        mState.setValue(Enums.state.Base);
    }

    public void switchToBase(String from) {
        mState.setValue(Enums.state.Base);
    }

    public void switchToOrder() {
        mState.setValue(Enums.state.Order);
    }

    public LiveData<Enums.state> getState() {
        return mState;
    }


    static class Enums {
        public enum state {
            Base,
            Login,
            Order
        }
    }

}
