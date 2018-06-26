package hu.elte.melvin.efoodsystem;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import hu.elte.melvin.efoodsystem.model.Food;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BaseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private BaseFragment.SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    public BaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BaseFragment newInstance(String param1, String param2) {
        BaseFragment fragment = new BaseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_base, container, false);
        MainViewModel mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainViewModel.isIsloggedIn().getValue()) {
                    if (!mainViewModel.isOrderEmpty()){
                        Snackbar.make(view, "Your order will now be placed", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        mainViewModel.switchToOrder();
                    }else {
                        Snackbar.make(view, "Please, first select food(s) of your choice", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                } else {
                    mainViewModel.switchToLogIn();
                }
            }
        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
//        ViewPager vpPager = (ViewPager) view.findViewById(R.id.vpPager);
//        vpPager.setClipToPadding(false);
        mViewPager.setPageMargin(12);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_FOOD_ID = "food_id";
        private static final String ARG_FOOD_NAME = "food_name";
        private static final String ARG_FOOD_DESCR = "food_descr";
        private static final String ARG_FOOD_PRICE = "food_price";
        private static final String ARG_FOOD_URL = "food_url";

        private int foodID;
        private String foodName;
        private String foodDescr;
        private String foodURL;
        private double foodprice;
        private CheckBox checkBoxSelected;
        private ImageView imageView;

        MainViewModel mainViewModel;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
//        public static BaseFragment.PlaceholderFragment newInstance(int sectionNumber) {
        public static BaseFragment.
                PlaceholderFragment newInstance(int foodID, String foodName, double foodPrice,
                                                String description, String foodURL) {
            BaseFragment.PlaceholderFragment fragment = new BaseFragment.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_FOOD_ID, foodID);
            args.putDouble(ARG_FOOD_PRICE, foodPrice);
            args.putString(ARG_FOOD_NAME, foodName);
            args.putString(ARG_FOOD_DESCR, description);
            args.putString(ARG_FOOD_URL, foodURL);
            fragment.setArguments(args);
            return fragment;
        }

        // Store instance variables based on arguments passed
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            foodID = getArguments().getInt(ARG_FOOD_ID);
            foodName = getArguments().getString(ARG_FOOD_NAME);
            foodDescr = getArguments().getString(ARG_FOOD_DESCR);
            foodURL = getArguments().getString(ARG_FOOD_URL);
            foodprice = getArguments().getDouble(ARG_FOOD_PRICE, 0.0);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View wVW = inflater.inflate(R.layout.fragment_main, container, false);
            View rootView = View.inflate(getContext(), R.layout.fragment_main, null);
            /*
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            */
            checkBoxSelected = (CheckBox)rootView.findViewById(R.id.checkBoxSelected);
            imageView = (ImageView)rootView.findViewById(R.id.imageView);
            int imgResId = this.getResources().getIdentifier(foodURL, "drawable", getActivity().getPackageName());
            mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
            imageView.setImageResource(imgResId);
            checkBoxSelected.setText(foodName + "    Price = " + foodprice);
            checkBoxSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                    if (mainViewModel.isIsloggedIn().getValue()) {
                        if (isChecked){
                            mainViewModel.addFoodForOrder(foodID, foodName, foodprice, foodDescr, foodURL);
                        }else {
                            mainViewModel.removeFoodFromOrder(foodID);
                        }
                    } else {
                        mainViewModel.switchToLogIn();
                    }
                }
            });
//TODO why?!
//            mainViewModel.getFoodOrder().observe(this, new Observer<Order>() {
//                @Override
//                public void onChanged(@Nullable Order order) {
//
//                }
//            });

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        /*
        MainViewModel mainViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
                .get(MainViewModel.class);
        private List<Food> allFoods = mainViewModel.getAllFoods();
        */
        private List<Food> allFoods;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            generateDummyFoods();
        }

        private void generateDummyFoods() {

            final String[] FIRST = new String[]{
                    "food in plate 1", "1000.0", "Just a food in plate", "food0"};
            final String[] SECOND = new String[]{
                    "food in plate 2", "2000.0", "Just a food in plate", "food1"};
            final String[] THIRD = new String[]{
                    "food in plate 3", "1500.0", "Just a food in plate", "food2"};
            final String[] FOURTH = new String[]{
                    "food in plate 4", "4000.0", "Just a food in plate", "food3"};
            final String[][] dummies = new String[][] {FIRST, SECOND, THIRD, FOURTH};

//            foodDao.deleteAllFoods();

            int id = 0;
            allFoods = new ArrayList<>();
            for (String[] dummy : dummies) {
                Food food = new Food();
                food.setId(id++);
                food.setFoodname(dummy[0]);
                food.setPrice(Double.parseDouble(dummy[1]));
                food.setDescription(dummy[2]);
                food.setImageURL(dummy[3]);
                allFoods.add(food);
            }

//            foodDao.insertFoods(foods.toArray(new Food[foods.size()]));

        }


        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Food food = allFoods.get(position);
            return BaseFragment.PlaceholderFragment.newInstance(food.getId(), food.getFoodname(), food.getPrice()
                                                            ,food.getDescription(), food.getImageURL());
        }

        @Override
        public int getCount() {
            // total number of pages.
//            Object one = allFoods.getValue();
//            return allFoods.getValue() == null ? 0 : allFoods.getValue().size();
            return allFoods.size();
        }
    }

}
