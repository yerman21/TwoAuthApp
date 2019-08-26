package com.yerman.twoauthapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.yerman.twoauthapp.R;
import com.yerman.twoauthapp.entity.User;
import com.yerman.twoauthapp.view.LoginActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User user;
    private final String TAG = "(MainActivity) ";
    private EditText txt_phone, txt_user_name;
    private FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recibiendo datos de LoginActivity
        try {
            user = (User) getIntent().getSerializableExtra("userData");
        }catch (Exception e){
            Log.d(TAG, "error inesperado");
        }

        if(user == null){
        //if(User.getUsername() == null || User.getUsername().isEmpty()){
            startActivity(new Intent(this, LoginActivity.class));
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            //if (savedInstanceState != null) {
            //    return;
            //}
            //LineFragmentLayout firstFragment = new LineFragmentLayout();
            //firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            //getSupportFragmentManager().beginTransaction()
            //        .add(R.id.fragment_container, ).commit();
        //}
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()) {
            case R.id.nav_home:

                break;
            case R.id.nav_generar_descuento:
                GenerarDescuentoFragment miFragment = new GenerarDescuentoFragment();
                miFragment.setArguments(getIntent().getExtras());
                transaction2.replace(R.id.fragment_container, miFragment);
                break;
            case R.id.nav_leer_qr:
                LeerDescuentoFragment leerDescuentoFragment = new LeerDescuentoFragment();
                leerDescuentoFragment.setArguments(getIntent().getExtras());
                transaction2.replace(R.id.fragment_container, leerDescuentoFragment);
                break;
            case R.id.nav_permitir_acceso:
                PermitirAccesoFragment permitirAccesoFragment = new PermitirAccesoFragment();
                permitirAccesoFragment.setArguments(getIntent().getExtras());
                transaction2.replace(R.id.fragment_container, permitirAccesoFragment);
                break;
            default:

                break;
        }
        transaction2.addToBackStack(null);
        transaction2.commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
