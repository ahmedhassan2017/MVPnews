package app.sunshine.com.example.android.mvpnews;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ahmed Hassan on 1/31/2018.
 */

public class News implements Parcelable {

    String title;
    String abstraction;
    String copyright;
    String multimedia;
    String byline;
     String caption;

    protected News(Parcel in) {
        title = in.readString();
        abstraction = in.readString();
        copyright = in.readString();
        caption = in.readString();
        byline = in.readString();
        multimedia = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(abstraction);
        dest.writeString(copyright);
        dest.writeString(caption);
        dest.writeString(byline);
        dest.writeString(multimedia);
    }
}
