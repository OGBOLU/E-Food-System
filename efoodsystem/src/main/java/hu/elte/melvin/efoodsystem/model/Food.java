package hu.elte.melvin.efoodsystem.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "food")
public class Food {
    //    [Food] id, name, price, description, ingredients, imageURL
    @PrimaryKey(autoGenerate = true)
    private int id;
//    @NonNull
    private String foodname; //for customer = email
//    @NonNull
    private double price;
    private boolean isMainDish = true;
    private String description;
    private String ingredients;
    private String imageURL;

    public Food(String foodname, double price, String description, String ingredients, String imageURL) {
        this.foodname = foodname;
        this.price = price;
        this.description = description;
        this.ingredients = ingredients;
        this.imageURL = imageURL;
    }

    public Food(){

    }

    public int getId() {
        return id;
    }

//    @NonNull
    public String getFoodname() {
        return foodname;
    }

//    @NonNull
    public double getPrice() {
        return price;
    }

    public boolean isMainDish() { return isMainDish; }

    public void setMainDish(boolean mainDish) {
        isMainDish = mainDish;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
