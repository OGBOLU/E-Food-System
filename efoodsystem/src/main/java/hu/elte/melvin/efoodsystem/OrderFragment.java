package hu.elte.melvin.efoodsystem;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import hu.elte.melvin.efoodsystem.model.Order;
import hu.elte.melvin.efoodsystem.model.OrderList;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Order order;
    private MainViewModel mainViewModel;
    SimpleCursorAdapter mAdapter;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        mainViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);
        order = mainViewModel.getFoodOrder().getValue();

        if (order != null){
            //TODO binding
            TextView txtCustomer = (TextView)view.findViewById(R.id.textViewCustomer);
            String strCustomer = String.valueOf(txtCustomer.getText()) + order.getCustomer().getUsername();
            txtCustomer.setText(strCustomer);
            TextView txtDate = (TextView)view.findViewById(R.id.textViewDate);
            String strDate = String.valueOf(txtDate.getText()) + order.getTimeOfOrder();
            txtDate.setText(strDate);
            TextView txtAmt = (TextView)view.findViewById(R.id.textViewTotalAMT);
            String strAmt = String.valueOf(txtAmt.getText()) + order.getTotalprice();
            txtAmt.setText(strAmt);


            LinearLayout layout = (LinearLayout)view.findViewById(R.id.list_container);
            for (OrderList item : order.getOrderLists()) {
                TextView detail = new TextView(view.getContext());
                detail.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                                        LinearLayout.LayoutParams.WRAP_CONTENT));
                detail.setText(item.getFood().getFoodname() + " ---- "+ item.getQuantity());
                layout.addView(detail);
            }

            ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollview);
            scrollView.invalidate();
            scrollView.requestLayout();

        }

        Button buttonPay = (Button)view.findViewById(R.id.buttonPay);
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Restaurated Foods");
                alertDialog.setMessage("Your Order has been successfully Processed. Thank you");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                mainViewModel.clearCurrentOrder();
                mainViewModel.switchToBase();
            }
        });

        return view;
    }

}
