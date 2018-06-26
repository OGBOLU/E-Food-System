package hu.elte.melvin.efoodsystem;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import hu.elte.melvin.efoodsystem.model.AppDatabase;
import hu.elte.melvin.efoodsystem.model.Food;
import hu.elte.melvin.efoodsystem.model.OrderDao;
import hu.elte.melvin.efoodsystem.model.OrderList;
import hu.elte.melvin.efoodsystem.model.User;
import hu.elte.melvin.efoodsystem.model.UserDao;

import java.util.Arrays;
import java.util.List;

public class AppDataRepository {

    private static AppDataRepository appDataRepo; //Only one Data Source per life

    private final AppDatabase db;

    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private OrderDao orderDao;
    private LiveData<List<OrderList>> userOrderList;
    private LiveData<List<OrderList>> allOrderList;

    private List<Food> allFoods;

    private AppDataRepository(final AppDatabase database){
        db = database;
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
        allFoods = Arrays.asList(db.foodDao().getAllFoods());
    }

    public static AppDataRepository getInstance(Application application) {
        if (appDataRepo == null) {
            synchronized (AppDataRepository.class) {
                if (appDataRepo == null) {
                    appDataRepo = new AppDataRepository(AppDatabase.getDatabase(application));
                }
            }
        }
        return appDataRepo;
    }

    public List<Food> getAllFoods() {
//        return allFoods;
        return  Arrays.asList(db.foodDao().getAllFoods()); //TODO SERIOUSLY - use AsycTask
        //https://stackoverflow.com/questions/44167111/android-room-simple-select-query-cannot-access-database-on-the-main-thread
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public User getUser(String username, String password){
        return userDao.getUser(username, password);
    }

    public void insert (User... users) {
        new insertAsyncTask(userDao).execute(users);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insertUsers(params[0]);
            return null;
        }
    }

    //  ORDER


}
