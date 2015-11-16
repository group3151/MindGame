package game.MindGame;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Инна on 16.11.2015.
 */
public class MathLevel extends Level {

    public MathLevel(ImageView imageView) {
        super(imageView);
    }

    @Override
    public Bitmap Next() {
        return null;
    }

    @Override
    public Bitmap getAdditionalBitmap() {
        return null;
    }
}
