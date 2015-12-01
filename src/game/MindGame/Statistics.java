package game.MindGame;

import android.content.Context;

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
    private Map<Integer, String> users = new TreeMap<Integer, String>();

    public Statistics(Context context)
    {
        this.c = context;
        addUser("Paul", 213);
        addUser("Dean", 11);
        addUser("Sam", 413);
        addUser("Jane", 113);
        addUser("Bill", 813);
        addUser("Carl", 5);
    }

    public void addUser(String name, Integer score) {
        users.put(score, name);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(c.openFileInput(fileName)));
            String str, str1;
            int integer;
            for (int i = 0; i <= 4; i++) {
                if ((str = br.readLine()) != null) {
                    integer = Integer.parseInt(str);
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


    public void save() {
        try {
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(c.openFileOutput(fileName, Context.MODE_PRIVATE)));
            for (Map.Entry e : users.entrySet()) {
                br.write(e.getKey() + "\n");
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
}

