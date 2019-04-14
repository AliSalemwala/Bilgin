package com.example.bilgin.com.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.bilgin.R;
import com.example.bilgin.com.models.Employee;

public class EarlySalaryFragment extends Fragment {
    Employee employee;
    EditText sal;
    AppCompatButton getSal;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EarlySalaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EarlySalaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EarlySalaryFragment newInstance(String param1, String param2) {
        EarlySalaryFragment fragment = new EarlySalaryFragment();
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
        View v = inflater.inflate(R.layout.fragment_early_salary, container, false);
        sal = v.findViewById(R.id.salary_advance);
        getSal = v.findViewById(R.id.get_salary_button);
        getSal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sal.getText().toString().isEmpty()){
                    Snackbar.make(v, "Enter a number please!", Snackbar.LENGTH_SHORT).show();
                } else{
                    float add = Float.parseFloat(sal.getText().toString());
                    employee.setCurrentBalance(add);
                    employee.setNexthMonthBalance(add);
                    if (!employee.isEligibleForLoan(add)){
                        Snackbar.make(v, "You cannot take such a large advance!", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(v, "You got your salary early!", Snackbar.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getActivity().setTitle("");
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        employee = mListener.getEmployee();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
