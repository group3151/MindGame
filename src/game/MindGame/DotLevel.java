package game.MindGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by DVitinnik on 04-Nov-15.
 */
public class DotLevel extends Level {
    private int radius;

    public DotLevel(ImageView imageView) {
        super(imageView);
        mark = 50;
        time = 2000;
        questionsCount = 20;
        radius = 50;
    }

    @Override
    public Bitmap getMainImage() {
        Bitmap bitmap = Bitmap.createBitmap(mainImage.getWidth(), mainImage.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Random rand = new Random(System.currentTimeMillis());
        x0 = Math.abs(rand.nextInt()) % (mainImage.getWidth() - 3 * radius) + radius;
        y0 = Math.abs(rand.nextInt()) % (mainImage.getHeight() - 3 * radius) + radius;


        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        canvas.drawCircle(x0, y0, radius, paint);

        x1 = x0 + radius;
        y1 = y0 + radius;

        x0 -= radius;
        y0 -= radius;

        return bitmap;
    }
}
