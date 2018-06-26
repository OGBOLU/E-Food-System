package hu.elte.melvin.efoodsystem.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {User.class, Food.class, Order.class, OrderList.class }, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract FoodDao foodDao();
    public abstract OrderDao orderDao();

    private static AppDatabase INSTANCE;

    //Callback to return the AppDatabase Instance Asynchronously + Populate with Data also Async-ly
    private static AppDatabase.Callback sAppDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    /**
     * Returns the Instance of the Database as wrapped by ROOM
     * @param
     * @return AppDatabase object
     */
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "restaurant_db")
                            .allowMainThreadQueries() //TODO temporary Escape from Async Loading
                            .fallbackToDestructiveMigration()
                            .addCallback(sAppDatabaseCallback) // to AsyncTask, deletes the contents of the database,
                            // then populates it with the two words "Hello" and "World"
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //TODO : Pre populate the DB
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDao userDao;
        private final FoodDao foodDao;
        private final OrderDao orderDao;

        PopulateDbAsync(AppDatabase db) {
            userDao = db.userDao();
            foodDao = db.foodDao();
            orderDao = db.orderDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            generateDummyUsers();
            generateDummyFoods();
            Log.i("AppDatase", "Dummy Data created successfully");
            return null;
        }

        private void generateDummyFoods() {
            /*
                private String foodname;
                private double price;
                private boolean isMainDish = true;
                private String description;
                private String ingredients;
                private String imageURL;
            */

            final String[] FIRST = new String[]{
                    "food in plate 1", "1000.0", "Just a food in plate", "food0"};
            final String[] SECOND = new String[]{
                    "food in plate 2", "2000.0", "Just a food in plate", "food1"};
            final String[] THIRD = new String[]{
                    "food in plate 3", "1500.0", "Just a food in plate", "food2"};
            final String[] FOURTH = new String[]{
                    "food in plate 4", "4000.0", "Just a food in plate", "food3"};
            final String[][] dummies = new String[][] {FIRST, SECOND, THIRD, FOURTH};

            foodDao.deleteAllFoods();

            List<Food> foods = new ArrayList<>();
            for (String[] dummy : dummies) {
                Food food = new Food();
                food.setFoodname(dummy[0]);
                food.setPrice(Double.parseDouble(dummy[1]));
                food.setDescription(dummy[2]);
                food.setImageURL(dummy[3]);
                foods.add(food);
            }

            foodDao.insertFoods(foods.toArray(new Food[foods.size()]));
            int i = foods.size();

            Log.i("AppDatase", "Dummy Food Data created successfully - Size " + foods.size());

        }

        private void generateDummyUsers() {

            /*
                this.id = id;
                this.username = username;
                this.role = role;
                this.companyID = companyID;
                this.password = password;
             */

            final String[] ROOT = new String[]{
                    "root", "root", "00007", "android"};
            final String[] MANAGER = new String[]{
                    "Manager", "manager", "", "hello"};
            final String[] STAFF = new String[]{
                    "Staff-1", "admin", "", "hello"};
            final String[] FIRST = new String[]{
                    "customer@first", "customer", "", "hello"};
            final String[] SECOND = new String[]{
                    "customer@second", "customer", "", "hello"};

            userDao.deleteAllUserss();

            List<User> users = new ArrayList<>();
            User root = new User();
            root.setUsername(ROOT[0]);
            root.setRole(ROOT[1]);
            root.setCompanyID(ROOT[2]);
            root.setPassword(ROOT[3]);
            users.add(root);

            User manager = new User();
            manager.setUsername(MANAGER[0]);
            manager.setRole(MANAGER[1]);
            manager.setCompanyID(MANAGER[2]);
            manager.setPassword(MANAGER[3]);
            users.add(manager);

            User admin = new User();
            admin.setUsername(STAFF[0]);
            admin.setRole(STAFF[1]);
            admin.setCompanyID(STAFF[2]);
            admin.setPassword(STAFF[3]);
            users.add(admin);

            User user = new User();
            user.setUsername(FIRST[0]);
            user.setRole(FIRST[1]);
            user.setCompanyID(FIRST[2]);
            user.setPassword(FIRST[3]);
            users.add(user);

            user = new User();
            user.setUsername(SECOND[0]);
            user.setRole(SECOND[1]);
            user.setCompanyID(SECOND[2]);
            user.setPassword(SECOND[3]);
            users.add(user);

            userDao.insertUsers(users.toArray(new User[users.size()]));
/*
            Random rnd = new Random();
            for (int i = 0; i < FIRST.length; i++) {
                for (int j = 0; j < SECOND.length; j++) {
                    ProductEntity product = new ProductEntity();
                    product.setName(FIRST[i] + " " + SECOND[j]);
                    product.setDescription(product.getName() + " " + DESCRIPTION[j]);
                    product.setPrice(rnd.nextInt(240));
                    product.setId(FIRST.length * i + j + 1);
                    users.add(product);
                }
            }
*/
        }
    }


}