
package vn.edu.vhu.ltdd.a8fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "A8_231A010310";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav =
                findViewById(R.id.bottomNav);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.tab_home) {
                doiTab(new HomeFragment());

            } else if (id == R.id.tab_news) {
                doiTab(new NewsListFragment());

            } else if (id == R.id.tab_profile) {
                doiTab(ProfileFragment.newInstance(
                        "Huỳnh Dương Gia Huy",
                        "231A010310"));
            }

            return true;
        });

        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.tab_home);
        }
    }

    private void doiTab(Fragment f) {
        Log.d(TAG, "Chuyển tab sang: "
                + f.getClass().getSimpleName());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, f)
                .setReorderingAllowed(true)
                .commit();
    }
}
