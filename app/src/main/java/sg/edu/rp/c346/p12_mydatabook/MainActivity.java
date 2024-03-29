package sg.edu.rp.c346.p12_mydatabook;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle drawerToggle;
    private String[] drawerItems;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    String currentTitle;
    ActionBar ab;
    boolean check = true;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerList);

            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.left_drawer);

        drawerItems = new String[] { "Bio", "Vaccination", "Anniversary", "About Us" };
        ab = getSupportActionBar();

        CustomAdapter adapter = new CustomAdapter(this, R.layout.row, drawerItems);
        drawerList.setAdapter(adapter);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                if (position == 3){
                    Intent intent = new Intent(MainActivity.this, AboutUs.class);
                    startActivity(intent);
                    return;
                }

                Fragment fragment = null;
                if (position == 0){
                    fragment = new BioFragment();
                } else if (position == 1){
                    fragment = new VaccinationFragment();
                } else if (position == 2){
                    fragment = new AnniversaryFragment();
                }

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                trans.replace(R.id.content_frame, fragment);
                trans.commit();

                drawerList.setItemChecked(position, true);
                currentTitle = drawerItems[position];
                ab.setTitle(currentTitle);
                drawerLayout.closeDrawer(drawerList);
            }
        });

        currentTitle = this.getTitle().toString();

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                ab.setTitle(currentTitle);
            }

            @Override
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                ab.setTitle("Make a selection");
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.addDrawerListener(drawerToggle);
        ab.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onPostCreate(Bundle saveInstanceState){
        // Sync toggle state so the indicator is shown properly.
        //  Have to call in onPostCreate()
        super.onPostCreate(saveInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}