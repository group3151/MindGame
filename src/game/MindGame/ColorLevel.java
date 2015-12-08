package game.MindGame;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by kirill on 17.11.2015.
 */
public class ColorLevel extends Level {
    private int radius;
    private int[] color;
    private Random rand;
    private int sampleCircle;
    private Paint paint;

    public ColorLevel(ImageView imageView) {
        super(imageView);
        radius = 40;
        mark = 50;
        time = 3000;
        questionsCount = 10;
    }

    @Override
    public Bitmap getMainImage() {
        Bitmap bitmap = Bitmap.createBitmap(mainImage.getWidth(), mainImage.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        int dx = 100;
        int dy = 100;

        x0 = mainImage.getWidth() / 2;
        y0 = mainImage.getHeight() / 2 - dy;

        rand = new Random(System.currentTimeMillis());
        color = new int[6];
        color[0] = Color.rgb(rand.nextInt() % 255, rand.nextInt() % 255, rand.nextInt() / 6 % 255);
        color[1] = Color.rgb(rand.nextInt() / 2 % 255, rand.nextInt() % 255, rand.nextInt() / 5 % 255);
        color[2] = Color.rgb(rand.nextInt() / 3 % 255, rand.nextInt() % 255, rand.nextInt() / 4 % 255);
        color[3] = Color.rgb(rand.nextInt() / 4 % 255, rand.nextInt() % 255, rand.nextInt() / 3 % 255);
        color[4] = Color.rgb(rand.nextInt() / 5 % 255, rand.nextInt() % 255, rand.nextInt() / 2 % 255);
        color[5] = Color.rgb(rand.nextInt() / 6 % 255, rand.nextInt() % 255, rand.nextInt() % 255);
        sampleCircle = Math.abs(rand.nextInt() % 6);

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setColor(color[sampleCircle]);
        canvas.drawCircle(x0, y0, radius, paint);

        paint.setColor(color[0]);
        canvas.drawCircle(x0 - dx, y0 + dy, radius, paint);
        paint.setColor(color[1]);
        canvas.drawCircle(x0 + dx, y0 + dy, radius, paint);
        paint.setColor(color[2]);
        canvas.drawCircle(x0 - dx, y0 + dy * 2, radius, paint);
        paint.setColor(color[3]);
        canvas.drawCircle(x0 + dx, y0 + dy * 2, radius, paint);
        paint.setColor(color[4]);
        canvas.drawCircle(x0 - dx, y0 + dy * 3, radius, paint);
        paint.setColor(color[5]);
        canvas.drawCircle(x0 + dx, y0 + dy * 3, radius, paint);

        if (sampleCircle % 2 == 0)
            x0 -= dx;
        else
            x0 += dx;
        if (sampleCircle > 3)
            y0 += dy * 3;
        else {
            if (sampleCircle > 1)
                y0 += dy * 2;
            else
                y0 += dy;
        }

        x1 = x0 + radius;
        y1 = y0 + radius;

        x0 -= radius;
        y0 -= radius;


        return bitmap;
    }
}
