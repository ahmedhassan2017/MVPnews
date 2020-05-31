package app.sunshine.com.example.android.mvpnews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

//        Intent sentIntent =getIntent();
//        Bundle sentBundel =sentIntent.getExtras();



        Bundle data = getIntent().getExtras();

        DetailedFragment detailedFragment = new DetailedFragment();
        detailedFragment.setArguments(data);
        getSupportFragmentManager().beginTransaction().replace(R.id.flDetailes,detailedFragment,"").commit();


    }
}
