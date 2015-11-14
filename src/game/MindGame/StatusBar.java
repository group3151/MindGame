package game.MindGame;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DVitinnik on 03-Nov-15.
 */
public class StatusBar implements Parcelable {
    public static final Creator<StatusBar> CREATOR = new StatusBarCreator();

    private int score = 0;
    private int level = 1;
    private int time = 1000;
    private int count=0;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public StatusBar() {
        clearStatus();
    }

    public StatusBar(int score, int level, int time) {
        this.score = score;
        this.level = level;
        this.time = time;
    }

    public void clearStatus() {
        score = 0;
        level = 1;
        time = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(String.valueOf((score)));
        parcel.writeString(String.valueOf((level)));
        parcel.writeString(String.valueOf((time)));
    }

    private static class StatusBarCreator implements Creator<StatusBar> {

        @Override
        public StatusBar createFromParcel(Parcel parcel) {
            int score = Integer.valueOf(parcel.readString());
            int level = Integer.valueOf(parcel.readString());
            int time = Integer.valueOf(parcel.readString());
            return new StatusBar(score, level, time);
        }

        @Override
        public StatusBar[] newArray(int size) {
            return new StatusBar[size];
        }
    }
}
