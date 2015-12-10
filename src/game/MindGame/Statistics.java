package game.MindGame;

import android.content.Context;
import android.os.Parcelable;
import android.os.Parcel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;


public class Statistics implements Parcelable {
    String fileName = "File.txt";
    TreeMap<Integer, String> users = new TreeMap<Integer, String>();
    Context c;

    public Statistics(Parcel in) {
        String s1 = in.readString();
        int a = in.readInt();
        String s = in.readString();
        this.users.put(a, s);
        this.fileName = s1;
    }

    public void setContext(Context context) {
        this.c = context;
    }

    public Statistics(Context context) {
        this.c = context;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public TreeMap getStatistics() {
        users.clear();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(c.openFileInput("File.txt")));
            String str, str1;
            int integer;
            for (int i = 0; i <= 4; i++) {
                if ((str = br.readLine()) != null) {
                    integer = -Integer.parseInt(str);
                    if ((str1 = br.readLine()) != null)
                        users.put(integer, str1);
                } else break;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }


    public void addUser(String name, Integer score) {
        users.put(-score, name);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(c.openFileInput(fileName)));
            String str, str1;
            int integer;
            for (int i = 0; i <= 4; i++) {
                if ((str = br.readLine()) != null) {
                    integer = Integer.parseInt(str);
                    if ((str1 = br.readLine()) != null)
                        users.put(-integer, str1);
                } else break;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        save();
    }


    private void save() {
        try {
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(c.openFileOutput(fileName, Context.MODE_PRIVATE)));
            for (Map.Entry<Integer, String> e : users.entrySet()) {
                br.write(-e.getKey() + "\n");
                br.write(e.getValue() + "\n");
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteStatistic() {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(c.openFileOutput(fileName, Context.MODE_PRIVATE)));
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fileName);
        for (Map.Entry<Integer, String> e : users.entrySet()) {
            parcel.writeInt(e.getKey());
            parcel.writeString(e.getValue());
        }
    }


    public static final Creator<Statistics> CREATOR = new StatisticsCreator();

    private static class StatisticsCreator implements Creator<Statistics> {

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


