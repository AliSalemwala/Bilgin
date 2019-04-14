package com.example.bilgin.com.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bilgin.R;
import com.example.bilgin.com.models.Employee;

public class UtilitiesFragment extends Fragment {
    private ImageButton electricity;
    private ImageButton gas;
    private ImageButton water;
    private ImageButton mobile;
    Employee employee;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public UtilitiesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UtilitiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UtilitiesFragment newInstance(String param1, String param2) {
        UtilitiesFragment fragment = new UtilitiesFragment();
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
        View v = inflater.inflate(R.layout.fragment_utilities, container, false);
        gas = v.findViewById(R.id.gas);
        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpActivity("Gas");
            }
        });

        electricity = v.findViewById(R.id.electricity);
        electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpActivity("Electricity");
            }
        });

        water = v.findViewById(R.id.water);
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpActivity("Water");
            }
        });

        mobile = v.findViewById(R.id.mobile);
        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpActivity("Mobile");
            }
        });


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
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
        employee = mListener.getEmployee();
        getActivity().setTitle(R.string.title_utilities_fragment);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void popUpActivity(String utility) {

        final String util = utility + " bill Paid: 100 TL";

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        builder.setTitle("Utility Payment");

        // Set an Icon for this Alert Dialog
        if (utility.equals("Electricity")) {
            builder.setTitle("Pay Electric Bill");
            builder.setIcon(R.drawable.electric);
        }
        else if( utility.equals("Gas"))
        {
            builder.setTitle("Pay Gas Bill");
            builder.setIcon(R.drawable.gas);
        }
        else if (utility.equals("Water"))
        {
            builder.setTitle("Pay Water Bill");
            builder.setIcon(R.drawable.water);

        }
        else
        {
            builder.setTitle("Pay Mobile Bill");
            builder.setIcon(R.drawable.mobile);
        }

        builder.setMessage("Enter Reference Number")
                .setPositiveButton("Pay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg0) {

                        //Employee makes utility payement.
                        float debt= 100;
                        employee.payUtilities(debt);

                        Toast.makeText(getContext(), util, Toast.LENGTH_SHORT).show();
                    }
                });

        EditText quantity = new EditText(getContext());
        quantity.setCursorVisible(true);
        quantity.setPadding(5, 0, 5, 0);

        quantity.setInputType(InputType.TYPE_CLASS_TEXT);


        LinearLayout ll=new LinearLayout(getContext());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(quantity);
        builder.setView(ll);

        builder.setCancelable(false);

        AlertDialog alertdialog = builder.create();

        // Show Alert Dialog
        alertdialog.show();

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
}
