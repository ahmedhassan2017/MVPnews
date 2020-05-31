package app.sunshine.com.example.android.mvpnews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */

public class HomeFragment extends Fragment implements MvpInterface {
    ListView listView;
    myPresenter.NewsAdapter adapter;
    myPresenter presenter;

    private NameListener listener;

    void setNamelistener(NameListener namelistener) {
        this.listener = namelistener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_home, container, false);

        listView = (ListView) fragment.findViewById(R.id.NewsList);
        // adapter and asynctask
        presenter=new myPresenter();
        presenter.setListener(this);
        presenter.startAdapterandexcuting(getContext());



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                myPresenter.NewsAdapter adapter = (myPresenter.NewsAdapter) parent.getAdapter();
                News news = (News) adapter.getItem(position);

                listener.setSelecteditem(news);


            }
        });


        return fragment;
    }

    @Override
    public void setadapterobject(myPresenter.NewsAdapter adapter) {

        this.adapter=adapter;
        listView.setAdapter(adapter);

    }
}
