package com.example.test1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener   {
    private Toolbar toolbar;
    private Button web;
    private Button back;
    private Button calcul;
    private Button call;
    private Button snackbar;
    private RelativeLayout main;
    NavigationView navigationView;
    ActionBarDrawerToggle toogle;
    DrawerLayout drawerLayout;
    Toolbar toolbar1;
    Intent intent;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);
        navigationView.bringToFront();
        toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        this.toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.web=(Button) findViewById(R.id.web);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.google.com/";
                Intent i= new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
                finish();
            }
        });
        this.back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), MainActivity4.class);
                startActivity(i);
                finish();
            }
        });
        this.calcul=(Button) findViewById(R.id.calcul);
        calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(i);
                finish();
            }
        });
        this.call= findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i= new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:123456789"));
                startActivity(i);

            }
        });

        this.snackbar=findViewById(R.id.snackbar);
        snackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar=Snackbar.make(drawerLayout,"Hello there!!",Snackbar.LENGTH_LONG).setAction("Hey",new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar snackbar1=Snackbar.make(drawerLayout,"Welcome!!",Snackbar.LENGTH_LONG);
                        snackbar1.show();
                    }
                }).setActionTextColor(Color.RED).setBackgroundTint(Color.YELLOW);
                snackbar.show();
            }
        });


        }
        public void onDialButton(View v){

                Intent  i= new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:123456789"));
                startActivity(i);


        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id =menuItem.getItemId();
        if(id==R.id.nav_home){
            return true;
        } else if (id == R.id.nav_calc) {
            Intent intent = new Intent(MainActivity.this, MainActivity3.class);
            startActivity(intent);
            return true;
        } else if (id==R.id.nav_share) {
            Toast.makeText(this, "Share", Toast.LENGTH_LONG).show();

            return true;
        }

//Fermer le menu
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        //Fermer le menu si je clique sur le bouton return
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}


