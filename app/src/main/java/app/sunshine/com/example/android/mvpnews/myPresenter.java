package app.sunshine.com.example.android.mvpnews;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 2/26/2018.
 */

public class  myPresenter {


    NewsAsyncTask task;
    private MvpInterface listener;

    public class NewsAdapter extends BaseAdapter {

        ArrayList<News> list;
        Context context;
        int counter = 0;

        public NewsAdapter(Context context, ArrayList<News> list) {
            this.context = context;
            this.list = list;
        }


//        public void Getview(ArrayList<News> list) {
//            this.list.clear();
//            this.list.addAll(list);
//            this.notifyDataSetChanged(); // call getview and update new data from list to gridview
//        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.news_list_item, null);
            }

            News news = (News) getItem(position);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageview);
            TextView textView = (TextView) convertView.findViewById(R.id.text);

            textView.setText(news.title);
            String poster = news.multimedia;
            counter++;
            Picasso.with(context).load(poster).into(imageView);

            return convertView;
        }
    }


    public class NewsAsyncTask extends AsyncTask<String, Void, ArrayList<News>> {

        NewsAdapter adapter;
        Context context;

        public NewsAsyncTask(Context c) {
            this.context = c;
        }


        @Override
        protected ArrayList<News> doInBackground(String... params) {

            ArrayList<News> list = new ArrayList<>();

            URL url = null;
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonstring;

            // Create the request to OpenWeatherMap, and open the connection
            try {
                url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonstring = buffer.toString();
                Log.e("doInBackground: ", jsonstring); // logging

                JSONObject jsondata = new JSONObject(jsonstring);  // keda ana 3andy kol el data .. 3ayez aasmha ba2a
                JSONArray arrayjson = jsondata.getJSONArray("results");  // 3mlt jsonarray ha2asam feh el data
                for (int i = 0; i < arrayjson.length(); i++) {
                    News news = new News(Parcel.obtain());
                    JSONObject jsonObjIndex = arrayjson.getJSONObject(i);
                    news.title = jsonObjIndex.getString("title");
                    news.abstraction = jsonObjIndex.getString("abstract");
                    news.byline = jsonObjIndex.getString("byline");


                    JSONArray multimedia = jsonObjIndex.getJSONArray("multimedia");
                    JSONObject multimediaJSONObject = multimedia.getJSONObject(2);
                    news.multimedia = multimediaJSONObject.getString("url");
                    news.caption = multimediaJSONObject.getString("caption");
                    news.copyright = multimediaJSONObject.getString("copyright");
                    list.add(news);
                }


            } catch (Throwable e1) {
                e1.printStackTrace();
                Log.e("Error print stacktrace", e1.toString());
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("Error closing stream", e.toString());
                    }
                }


                return list;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {

            if (news != null)

            {
                adapter = new myPresenter.NewsAdapter(context, news);
                listener.setadapterobject(adapter);
            }

        }
    }


    public void startAdapterandexcuting(Context context) {


        task = new myPresenter.NewsAsyncTask(context);
        task.execute("http://api.nytimes.com/svc/topstories/v2/health.json?api-key=5d28c38444c9424fb6f4c19a89de01bc");


    }

    public void setListener(MvpInterface listener)
    {
        this.listener = listener;
    }


}