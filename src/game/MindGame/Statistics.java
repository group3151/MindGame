package game.MindGame;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Map;
import java.util.TreeMap;


public class Statistics implements Parcelable {

    TreeMap<Integer, String> users=new TreeMap<Integer, String>();


    public Statistics(Parcel in){
        int a = in.readInt();
        String s = in.readString();
        this.users.put(a,s);
    }

    public Statistics( String name,int score ){
        int a = score;
        String s = name;
        this.users.put(a, s);
    }

    public Statistics(){
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel parcel, int i) {
        for(Map.Entry<Integer,String> e : users.entrySet()) {
            parcel.writeInt(e.getKey());
            parcel.writeString(e.getValue());
        }
    }


    public static final Creator<Statistics> CREATOR = new StatisticsCreator();
    private static class StatisticsCreator implements Creator<Statistics>
    {

        @Override
        public Statistics createFromParcel(Parcel parcel) {
            return new Statistics(parcel);
        }

        @Override
        public Statistics[] newArray(int i) {
            return new Statistics[i];
        }
    }
}


