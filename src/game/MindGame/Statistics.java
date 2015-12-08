package game.MindGame;

import android.content.Context;
import android.widget.TextView;
import android.app.Activity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;


public class Statistics {

    private static String fileName = "File.txt";
    Context c;
    Activity activity;
    private Map<Integer, String> users = new TreeMap<Integer, String>();

    public void Test() {
        addUser("Paul", 213);
        addUser("Yoda", 1413);
        addUser("Anna", 11);
        addUser("Sam", 413);
        addUser("Pete", 113);
        addUser("Bill", 813);
        addUser("Carl", 5);
    }

    public Statistics(Context context) {
        this.c = context;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void deleteStatistics() {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(c.openFileOutput(fileName, Context.MODE_PRIVATE)));
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        readStatistic();
    }

    public void addUser(String name, Integer score) {
        users.put(0 - score, name);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(c.openFileInput(fileName)));
            String str, str1;
            int integer;
            for (int i = 0; i <= 4; i++) {
                if ((str = br.readLine()) != null) {
                    integer = 0 - Integer.parseInt(str);
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
            users.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readStatistic() {
        final int N = 10;

        TextView t = (TextView) activity.findViewById(R.id.TextView0);
        TextView t1 = (TextView) activity.findViewById(R.id.TextView1);
        TextView t2 = (TextView) activity.findViewById(R.id.TextView2);
        TextView t3 = (TextView) activity.findViewById(R.id.TextView3);
        TextView t4 = (TextView) activity.findViewById(R.id.TextView4);
        TextView t5 = (TextView) activity.findViewById(R.id.TextView5);
        TextView t6 = (TextView) activity.findViewById(R.id.TextView6);
        TextView t7 = (TextView) activity.findViewById(R.id.TextView7);
        TextView t8 = (TextView) activity.findViewById(R.id.TextView8);
        TextView t9 = (TextView) activity.findViewById(R.id.TextView9);

        Object[] arr = new Object[N];
        arr[0] = t;
        arr[1] = t1;
        arr[2] = t2;
        arr[3] = t3;
        arr[4] = t4;
        arr[5] = t5;
        arr[6] = t6;
        arr[7] = t7;
        arr[8] = t8;
        arr[9] = t9;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(c.openFileInput(fileName)));
            String str, str1;
            for (int i = 0; i <= N - 1; i += 2) {
                if ((str = br.readLine()) != null) {
                    for (int k = 5 - str.length() - 1; k >= 0; k--)
                        str = "0" + str;
                    TextView text = (TextView) arr[i + 1];
                    text.setText(str);
                } else {
                    TextView text = (TextView) arr[i + 1];
                    text.setText("");
                }
                if ((str1 = br.readLine()) != null) {

                    TextView text1 = (TextView) arr[i];
                    text1.setText(str1);
                } else {
                    TextView text1 = (TextView) arr[i];
                    text1.setText("");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

