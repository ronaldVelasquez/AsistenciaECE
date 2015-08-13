package com.inei.asistenciaece.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.inei.asistenciaece.R;
import com.inei.asistenciaece.Utils.SessionManager;
import com.inei.asistenciaece.fragments.ConsolidatedFragment;
import com.inei.asistenciaece.fragments.PresenceClassFragment;
import com.inei.asistenciaece.fragments.PresenceFragment;
import com.inei.asistenciaece.fragments.ReportLocalFragment;
import com.inei.asistenciaece.fragments.ReportMainFragment;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private SessionManager sessionManager;
    private String password;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar(); // Setear Toolbar como action bar
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, Object> user = sessionManager.getUserDetails();
        password = String.valueOf(user.get(SessionManager.KEY_PASSWORD));
        username = String.valueOf(user.get(SessionManager.KEY_USUARIO));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        String drawerTitle = getResources().getString(R.string.present_item);
        if (savedInstanceState == null) {
            selectItem(drawerTitle);
        }

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marcar item presionado
                        menuItem.setChecked(true);
                        // Crear nuevo fragmento
                        String title = menuItem.getTitle().toString();
                        if (title.equals(getString(R.string.close_session_item))){
                            sessionManager.logoutUser();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            selectItem(title);
                        }
                        return true;
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            //getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectItem(String title) {
        // Enviar título como arguemento del fragmento
        Bundle args = new Bundle();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;
        switch (title){
            case "Asistencia al Local":
                args.putString(com.inei.asistenciaece.fragments.PresenceFragment.ARG_SECTION_TITLE, title);
                fragment = PresenceFragment.newInstance(title);
                fragment.setArguments(args);
                break;
            /*case "Asistencia en Aula":
                args.putString(com.inei.asistenciaece.fragments.PresenceClassFragment.ARG_SECTION_TITLE, title);
                fragment = PresenceClassFragment.newInstance(title);
                fragment.setArguments(args);
                break;*/
            case "Reportes":
                args.putString(ReportMainFragment.ARG_SECTION_TITLE, title);
                fragment = ReportMainFragment.newInstance(title);
                fragment.setArguments(args);
                break;
            case "Consolidado":
                args.putString(com.inei.asistenciaece.fragments.ConsolidatedFragment.ARG_SECTION_TITLE, title);
                args.putString(com.inei.asistenciaece.fragments.ConsolidatedFragment.ARG_PASSWORD, password);
                args.putString(com.inei.asistenciaece.fragments.ConsolidatedFragment.ARG_USERNAME, username);
                fragment = ConsolidatedFragment.newInstance(title, password, username);
                fragment.setArguments(args);
                break;
            default:
                args.putString(PresenceFragment.ARG_SECTION_TITLE, title);
                fragment = PresenceFragment.newInstance(title);
                fragment.setArguments(args);
                break;
        }
        fragmentManager.beginTransaction().replace(R.id.main_content, fragment).commit();
        drawerLayout.closeDrawers(); // Cerrar drawer
        setTitle(title); // Setear título actual
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 || super.onKeyDown(keyCode, event);
    }
}
