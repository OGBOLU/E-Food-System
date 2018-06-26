package com.example.access_pc.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.access_pc.dao.Dish;
import com.example.access_pc.e_food.R;
import com.example.access_pc.fragments.DishFragment.OnListFragmentInteractionListener;
import com.example.access_pc.fragments.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyDishRecyclerViewAdapter extends RecyclerView.Adapter<MyDishRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Dish> dishes;
    private final OnListFragmentInteractionListener mListener;

    public MyDishRecyclerViewAdapter(ArrayList<Dish> dishes, OnListFragmentInteractionListener listener) {
        this.dishes = dishes;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_dish, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = dishes.get(position);
        //holder.dishImg.setText(dishes.get(position).id);
        holder.dishName.setText(dishes.get(position).getDishName());
        holder.price.setText(dishes.get(position).getPrice().toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView dishImg;
        public final TextView dishName;
        public final TextView price;
        public Dish mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            dishImg = (ImageView) view.findViewById(R.id.dishImg);
            dishName = (TextView) view.findViewById(R.id.abbr1);
            price = (TextView) view.findViewById(R.id.fullName1);
        }

    }
}
