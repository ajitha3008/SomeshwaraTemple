package braingalore.someshwaratemple;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import braingalore.someshwaratemple.fragments.MantrasFragment;
import braingalore.someshwaratemple.fragments.AbhyasasExpandableFragment;
import braingalore.someshwaratemple.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager;
            FragmentTransaction fragmentTransaction;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    HomeFragment homeFragment = new HomeFragment();
                    fragmentTransaction.replace(R.id.fragment_container, homeFragment, "Home");
                    fragmentTransaction.commitAllowingStateLoss();
                    return true;
                case R.id.navigation_asanas:
                    fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    AbhyasasExpandableFragment asanasFragment = new AbhyasasExpandableFragment();
                    fragmentTransaction.replace(R.id.fragment_container, asanasFragment, "Abyasa");
                    fragmentTransaction.commitAllowingStateLoss();
                    return true;
                case R.id.navigation_mantras:
                    fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    MantrasFragment notificationFragment = new MantrasFragment();
                    fragmentTransaction.replace(R.id.fragment_container, notificationFragment, "Mantras");
                    fragmentTransaction.commitAllowingStateLoss();
                    return true;
            }
            return false;
        }
    };

    public void enableBackInSupportActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void disableBackInSupportActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        /*FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AbhyasasExpandableFragment asanasFragment = new AbhyasasExpandableFragment();
        fragmentTransaction.replace(R.id.fragment_container, asanasFragment, "dashboard");
        fragmentTransaction.commit();*/
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
    }

}
