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


    public ColorLevel(ImageView imageView, ImageView additionalImage) {
        super(imageView, additionalImage);
        radius = 50;
        mark = 50;
        time = 3000;
        questionsCount = 10;
        haveAddittionalImage = true;
        additionalImageCount = 2;
        additionalTime = 10;


        rand = new Random(System.currentTimeMillis());
        color = new int[6];
        color[0] = Color.rgb(rand.nextInt() % 255, rand.nextInt() % 255, rand.nextInt() % 255);
        color[1] = Color.rgb(rand.nextInt() % 255, rand.nextInt() % 255, rand.nextInt() % 255);
        color[2] = Color.rgb(rand.nextInt() % 255, rand.nextInt() % 255, rand.nextInt() % 255);
        color[3] = Color.rgb(rand.nextInt() % 255, rand.nextInt() % 255, rand.nextInt() % 255);
        color[4] = Color.rgb(rand.nextInt() % 255, rand.nextInt() % 255, rand.nextInt() % 255);
        color[5] = Color.rgb(rand.nextInt() % 255, rand.nextInt() % 255, rand.nextInt() % 255);
        sampleCircle = Math.abs(rand.nextInt() % 5);


        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
    }


    @Override
    public boolean TryClick(float x, float y) {
        return x >= x0 && x <= x1 && y >= y0 && y <= y1;
    }


    @Override
    public Bitmap getMainImage() {
        Bitmap bitmap = Bitmap.createBitmap(mainImage.getWidth(), mainImage.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        //x0=additionalImage.getWidth()/2;
        //y0=additionalImage.getHeight()/2;
        //paint.setColor(Color.rgb(199, 191, 230));
        //canvas.drawCircle(x0, y0, radius+10, paint);

        x0 = mainImage.getWidth() / 2;
        y0 = mainImage.getHeight() / 2;
        paint.setColor(Color.RED);
        paint.setColor(color[sampleCircle]);
        canvas.drawCircle(x0, y0, radius, paint);


        x1 = x0 + radius;
        y1 = y0 + radius;


        x0 -= radius;
        y0 -= radius;


        return bitmap;
    }


    @Override
    public Bitmap getAdditionalImage() {
        Bitmap bitmap = Bitmap.createBitmap(additionalImage.getWidth(), additionalImage.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);


        x0 = additionalImage.getWidth() / 2;
        y0 = additionalImage.getHeight() / 2;
        //paint.setColor(color[sampleCircle]);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(x0, y0, radius + 30, paint);


        return bitmap;
    }
}
