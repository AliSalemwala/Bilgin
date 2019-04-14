package com.example.bilgin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bilgin.com.fragments.BlankFragment;
import com.example.bilgin.com.fragments.EarlySalaryFragment;
import com.example.bilgin.com.fragments.HomeFragment;
import com.example.bilgin.com.fragments.LoanListFragment;
import com.example.bilgin.com.fragments.OnFragmentInteractionListener;
import com.example.bilgin.com.fragments.PayFragment;
import com.example.bilgin.com.fragments.PlusOneFragment;
import com.example.bilgin.com.fragments.UtilitiesFragment;
import com.example.bilgin.com.models.Employee;
import com.example.bilgin.com.models.EmployeeBuilder;

public class DisplayActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {
    Employee employee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EmployeeBuilder eB = new EmployeeBuilder();
        employee = eB.getEmployee("Ali");
        employee.requestLoan(45);
        employee.requestLoan(81);
        employee.requestLoan(12);
        //employee = eB.getEmployee((getIntent().getExtras().getString("empName")));
        //Toast.makeText(this, employee.getName(), Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.display_frame, new HomeFragment());
                ft.commit();
            }
        });
        fab.bringToFront();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //if (employee != null && !employee.getName().equals("ALI")){
            //employee = new Employee("ali", "qwe", "qwert", 50);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.display_frame, new HomeFragment());
            ft.commit();
        //}
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment;
        if (id == R.id.card_pay) {
            fragment = new PayFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.display_frame, fragment);
            ft.commit();

//            Snackbar.make(item.getActionView(), "tufhjb", Snackbar.LENGTH_LONG);
            //setContentView(R.layout.fragment_plus_one);
            // Handle the camera action
        } else if (id == R.id.take_loan) {
            fragment = new EarlySalaryFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.display_frame, fragment);
            ft.commit();
        } else if (id == R.id.my_loans) {
            fragment = new LoanListFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.display_frame, fragment);
            ft.commit();


        } else if (id == R.id.utilities) {
            fragment = new UtilitiesFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.display_frame, fragment);
            ft.commit();

        } else if (id == R.id.log_out) {
            Intent intent = new Intent(DisplayActivity.this, MainActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public Employee getEmployee() {
        return employee;
    }
}
