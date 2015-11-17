package game.MindGame;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Инна on 16.11.2015.
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

    public MathLevel(ImageView imageView) {
        super(imageView);
    }

    @Override
    public boolean TryClick(float x, float y) {
        return false;
    }

}
