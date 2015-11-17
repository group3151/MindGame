package game.MindGame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by DVitinnik on 20-Oct-15.
 */
public class GameFieldActivity extends Activity implements View.OnTouchListener {
    private int[] levelIndexList;
    private int levelIndex;
    private TimerAsync timerAsync;
    private Settings settings;
    private Level currentLevel = null;
    private StatusBar statusBar = new StatusBar();

    private ImageView imageView;
    private ImageView additionalImage;

    TextView timeTextView;
    TextView levelTextView;
    TextView scoreTextView;

    boolean gameOver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        // чтобы приложение постоянно имело портретную ориентацию
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //чтобы приложение было полноэкранным
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.gamefield);

        Button startGameButton = (Button) findViewById(R.id.startButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToNextLevel();
                startGameButton.setVisibility(View.GONE);
                timerAsync = new TimerAsync();
                timerAsync.execute(currentLevel.getTime());
                timeTextView.setWidth(imageView.getWidth() / 3);
                levelTextView.setWidth(imageView.getWidth() / 3);
                scoreTextView.setWidth(imageView.getWidth() / 3);
                updateStatusBar();
                gameOver = false;
            }
        });
        settings = getIntent().getParcelableExtra("settings");

        levelIndexList = settings.getLevelNumbers();
        levelIndex = -1;

        imageView = (ImageView) findViewById(R.id.image);
        imageView.setOnTouchListener(this);

        additionalImage = (ImageView) findViewById(R.id.additionalImage);

        timeTextView = (TextView) findViewById(R.id.timeTextView);
        levelTextView = (TextView) findViewById(R.id.levelTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
    }

    private void updateStatusBar() {
        timeTextView.setText("Время: " + String.valueOf(statusBar.getTime()));
        levelTextView.setText("Количество: " + String.valueOf(statusBar.getCount()));
        scoreTextView.setText("Счет: " + String.valueOf(statusBar.getScore()));
    }

    private boolean goToNextLevel() {
        if (levelIndex < levelIndexList.length - 1) {
            levelIndex++;

            currentLevel = settings.getLevel(levelIndexList[levelIndex], imageView);
            imageView.setImageBitmap(currentLevel.getMainImage());

            if (currentLevel.isHaveAddittionalImage())
                additionalImage.setVisibility(View.VISIBLE);
            else
                additionalImage.setVisibility(View.GONE);

            statusBar.setLevel(levelIndex + 1);
            statusBar.setTime(currentLevel.getTime());
            statusBar.setCount(currentLevel.getQuestionsCount());
            updateStatusBar();
            return true;
        } else {
            timerAsync.cancel(true);
            printText("YOU WIN");
        }
        return false;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (gameOver)
        {
            timerAsync.cancel(true);
            return false;
        }

        float x = motionEvent.getX();
        float y = motionEvent.getY();

        if (currentLevel != null && currentLevel.TryClick(x, y)) {
            statusBar.setCount(statusBar.getCount() - 1);
            statusBar.setScore(statusBar.getScore() + currentLevel.getMark());
            statusBar.setTime(currentLevel.getTime());

            if (currentLevel.isHaveAddittionalImage()) {
                for (int i = 0; i < currentLevel.getAdditionalImageCount(); i++) {
                    additionalImage.setImageBitmap(currentLevel.getAdditionalImage());
                    if (currentLevel.getAdditionalTime() > 0) {
                        try {
                            Thread.sleep(currentLevel.getAdditionalTime());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            imageView.setImageBitmap(currentLevel.getMainImage());

            timerAsync.cancel(true);
            timerAsync = new TimerAsync();
            timerAsync.execute(currentLevel.getTime());
        } else
            statusBar.setScore(statusBar.getScore() - 10);
        updateStatusBar();
        if (statusBar.getCount() == 0) {
            goToNextLevel();
            return false;
        }
        return true;
    }


    private void printText(String text) {
        Bitmap bitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        int x = 50;
        int y = imageView.getHeight() / 2;
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(150);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        canvas.drawText(text, x, y, paint);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onStop() {
        // timerAsync.cancel(false);
        // additionalTimer.cancel(false);
    }

    class TimerAsync extends AsyncTask<Integer, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... values) {
            while (values[0] > 0 && !isCancelled()) {
                statusBar.setTime(--values[0]);
                publishProgress();
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            updateStatusBar();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            gameOver = true;
            printText("GAME OVER");
        }
    }
}