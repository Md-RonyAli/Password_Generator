package com.example.savepass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.savepass.databinding.ActivityHomepageVersion2Binding;

public class Homepage_Version2 extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean savelogininfo;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private DrawerLayout drawerLayout;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomepageVersion2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("loginusername", MODE_PRIVATE);
        editor = sharedPreferences.edit();


        binding = ActivityHomepageVersion2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /*Intent in = getIntent();
        int i = in.getIntExtra("id", -1);
        if (i == 1) {
            FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.drawer_layout2, new Password_new()).commit();
            toolbar.setTitle("Passwords");
            //R.id.nav_pass2.
        } else if (i == 2) {
            FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.drawer_layout2, new Notes_new()).commit();
            toolbar.setTitle("Secure Notes");
        } else if (i == 3) {
            FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.drawer_layout2, new Address_new()).commit();
            toolbar.setTitle("Addresses");
        }
*/

        //setSupportActionBar(binding.appBarHomepageVersion2.toolbar2);
        binding.appBarHomepageVersion2.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent l = new Intent(Homepage_Version2.this, add_items.class);
                startActivity(l);


                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
        //DrawerLayout drawer = binding.drawerLayout;
        /*fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        } else {*/
            NavigationView navigationView = binding.navView;

            View headerView = navigationView.getHeaderView(0);
            TextView navusername = (TextView) headerView.findViewById(R.id.header_username);
            TextView email = (TextView) headerView.findViewById(R.id.emailview);

            savelogininfo = sharedPreferences.getBoolean("saveusername", true);
            if (savelogininfo == true) {
                navusername.setText(sharedPreferences.getString("username", null));
                email.setText(sharedPreferences.getString("email", null));
            }

            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            //Fragment fragment = getFragmentManager()
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_pass2, R.id.nav_notes2, R.id.nav_address2, R.id.nav_aboutus2, R.id.nav_share2, R.id.nav_setting2)
                    .setDrawerLayout(drawerLayout)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_homepage_version2);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public void onBackPressed() {
if (drawerLayout.isDrawerOpen(GravityCompat.START))
{
    drawerLayout.closeDrawer(GravityCompat.START);
}
else {
    super.onBackPressed();
}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage__version2, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_homepage_version2);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}