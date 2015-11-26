package game.MindGame;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by ���� on 16.11.2015.
 */
public class MathLevel extends Level {

    @Override
    public Bitmap getMainImage() {
        return null;
    }

    @Override
    public Bitmap getAdditionalImage() {
        return null;
    }

    public MathLevel(ImageView imageView, ImageView additionalImage)
    {
        super(imageView, additionalImage);
        time = 3000;
        mark = 100;
        questionsCount = 10;
        haveAddittionalImage = false;

    }

    @Override
    public boolean TryClick(float x, float y) {

            return x >= x0 && x <= x1 && y >= y0 && y <= y1;
    }

}
