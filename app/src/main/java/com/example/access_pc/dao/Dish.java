package com.example.access_pc.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Access_pc on 24/04/2018.
 */

public class Dish {
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("rest_name")
    @Expose
    private String restName;
    @SerializedName("mgr_name")
    @Expose
    private String mgrName;
    @SerializedName("dish_name")
    @Expose
    private String dishName;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("ingredient")
    @Expose
    private String ingredient;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
