package game.MindGame;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by DVitinnik on 20-Oct-15.
 */
public class Statistics implements Parcelable {
    private static String fileName="";
    private Dictionary<String, Integer> users=new Hashtable<String, Integer>();

    public static final Creator<Statistics> CREATOR = new StatisticsCreator();

    public void addUser(String name, Integer score)
    {
        users.put(name, score);
    }

    public void clear()
    {
     //   users.
    }

    public void save()
    {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }



    private static class StatisticsCreator implements Creator<Statistics>
    {

        @Override
        public Statistics createFromParcel(Parcel parcel) {
            return null;
        }

        @Override
        public Statistics[] newArray(int i) {
            return new Statistics[i];
        }
    }
}
