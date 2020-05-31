package app.sunshine.com.example.android.mvpnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements NameListener{



        int counter =0;
        boolean IsTwoPane =false;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            HomeFragment homeFragment = new HomeFragment();
            // the activity will be the listener for the main activity
            homeFragment.setNamelistener(this);
            getSupportFragmentManager().beginTransaction().add(R.id.flMain, homeFragment, "").commit();

            // check if two pane
            if (null != findViewById(R.id.flDetailes)) {
                IsTwoPane = true;
            }



        }

        @Override
        public void setSelecteditem(News item) {
            if (!IsTwoPane) {
                Intent intent = new Intent(getApplicationContext(),DetailedActivity.class);


//            intent.putExtra("title" , item.title);
//            intent.putExtra("abstraction" , item.abstraction);
//            intent.putExtra("url" , item.url);
//            intent.putExtra("shorturl" , item.short_url);
//            intent.putExtra("thumb" , item.thumbnail_standard);
//            intent.putExtra("source" , item.source);
//            intent.putExtra("multimedia" , item.multimedia[counter]);
//            counter++;

                intent.putExtra("item",item);
                startActivity(intent);
            }
            else
            {
                DetailedFragment detailedFragment = new DetailedFragment();

                Bundle bundle = new Bundle();
//            bundle.putString("title" , item.title);
//            bundle.putString("abstraction" , item.abstraction);
//            bundle.putString("url" , item.url);
//            bundle.putString("shorturl" , item.short_url);
//            bundle.putString("thumb" , item.thumbnail_standard);
//            bundle.putString("source" , item.source);
//            bundle.putString("multimedia" , item.multimedia[counter]);
//            counter++;
                bundle.putParcelable("item",item);


                detailedFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.flDetailes, detailedFragment, "").commit();

            }
        }
    }
