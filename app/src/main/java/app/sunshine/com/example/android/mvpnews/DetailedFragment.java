package app.sunshine.com.example.android.mvpnews;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailedFragment extends Fragment {


    public DetailedFragment() {
        // Required empty public constructor
    }

    int counter= 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View fragment = inflater.inflate(R.layout.fragment_detailed, container, false);


//        Bundle sentBundle =getArguments();
//        String title =sentBundle.getString("title");
//        String abstraction =sentBundle.getString("abstraction");
//        String url =sentBundle.getString("url");
//        String short_url =sentBundle.getString("shorturl");
//        String thumbnail_standard =sentBundle.getString("thumb");
//        String source =sentBundle.getString("source");
//        String multimedia=sentBundle.getString("multimedia");

        Bundle data = getArguments();
        News news = data.getParcelable("item");


        TextView caption = (TextView) fragment.findViewById(R.id.Caption);
        caption.append(news.caption);

        TextView Title = (TextView) fragment.findViewById(R.id.Abstraction);
        Title.append(news.abstraction);

//        TextView Source = (TextView) fragment.findViewById(R.id.author);
//        Source.setText("source: "+news.source);

        ImageView imageView = (ImageView) fragment.findViewById(R.id.newsimage);
        String poster=news.multimedia;
        Picasso.with(getActivity().getApplicationContext()).load(poster).into(imageView);

        TextView Byline = (TextView) fragment.findViewById(R.id.Byline);
        Byline.append(news.byline);

        TextView copyrights = (TextView) fragment.findViewById(R.id.Copyrights);
        copyrights.append(news.copyright);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) fragment.findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitle(news.title);


        return fragment;
    }

}
