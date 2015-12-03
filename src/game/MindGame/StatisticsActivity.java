package game.MindGame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;


public class StatisticsActivity extends Activity {

    private static String fileName = "File.txt";
    private TreeMap<Integer, String> users = new TreeMap<Integer, String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);
        getActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Statistics statistics =  getIntent().getParcelableExtra("statistics");
        TreeMap<Integer, String> users = statistics.users;
        for(Map.Entry<Integer,String> e : users.entrySet()) {
            if(e.getValue() != null)
                addUser(e.getValue(),e.getKey());
        }
        readStatistic();
    }

    public void readStatistic() {
        final int N = 10;

        TextView t  = (TextView) findViewById(R.id.TextView0);        TextView t1 = (TextView) findViewById(R.id.TextView1);
        TextView t2 = (TextView) findViewById(R.id.TextView2);        TextView t3 = (TextView) findViewById(R.id.TextView3);
        TextView t4 = (TextView) findViewById(R.id.TextView4);        TextView t5 = (TextView) findViewById(R.id.TextView5);
        TextView t6 = (TextView) findViewById(R.id.TextView6);        TextView t7 = (TextView) findViewById(R.id.TextView7);
        TextView t8 = (TextView) findViewById(R.id.TextView8);        TextView t9 = (TextView) findViewById(R.id.TextView9);

        Object[] arr = new Object[N];
        arr[0] = t;        arr[1] = t1;        arr[2] = t2;        arr[3] = t3;        arr[4] = t4;
        arr[5] = t5;       arr[6] = t6;        arr[7] = t7;        arr[8] = t8;        arr[9] = t9;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(fileName)));
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



    public void deleteStatistic(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View view1 = li.inflate(R.layout.dialoguewindow1, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setView(view1);

        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(fileName, Context.MODE_PRIVATE)));
                    bw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                readStatistic();
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    public void addUser(String name, Integer score) {
        users.put(-score, name);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(fileName)));
            String str, str1;
            int integer;
            for (int i = 0; i <= 4; i++) {
                if ((str = br.readLine()) != null) {
                    integer = -Integer.parseInt(str);
                    if ((str1 = br.readLine()) != null)
                      users.put(integer, str1);
                }else break;
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
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(openFileOutput(fileName, Context.MODE_PRIVATE)));
            for (Map.Entry<Integer, String> e : users.entrySet()) {
                br.write(-e.getKey()   + "\n");
                br.write(e.getValue() + "\n");
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

