package com.example.access_pc.fragments.dummy;

import com.example.access_pc.dao.Dish;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyDish {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Dish> ITEMS = new ArrayList<Dish>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Dish> ITEM_MAP = new HashMap<String, Dish>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDish(i));
        }
    }

    private static void addItem(Dish item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    private static Dish createDish(int position) {
        String createDate = "31/05/2018";
        String id;
        String restName = "restName";
        String mgrName = "mgrName";
        String dishName = "dishName";
        String imgUrl = "http://2.bp.blogspot.com/-1lr-ofAWmw4/VWhieIlZ6dI/AAAAAAAAIPg/eZypfzqLY80/s1600/egusi%2Bsoup.JPG";
        Double price = 100.5;
        String ingredient = "Cow Head, Crab, Egwusi, Ugu";
        Integer v = 0;

        Dish dish = new Dish();
        dish.setDishName(dishName + "-" + position);
        dish.setId(""+position);
        dish.setImgUrl(imgUrl);
        dish.setIngredient(ingredient);
        dish.setMgrName(mgrName + "-" + position);
        dish.setPrice(price);
        dish.setRestName(restName + "-" + position);
        dish.setV(0);

        return dish;
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
 /*   public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }*/
}
