package game.MindGame;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by DVitinnik on 04-Nov-15.
 */
public abstract class Level {
    protected boolean haveAddittionalBitmap;
    protected int time;
    protected int mark;
    protected int count;
    protected int x0, y0, x1, y1;
    protected ImageView imageView;

    public boolean isHaveAddittionalBitmap() {
        return haveAddittionalBitmap;
    }

    public Level(ImageView imageView) {
        this.imageView = imageView;
        haveAddittionalBitmap = false;
    }

    public abstract boolean TryClick(float x, float y);

    public abstract Bitmap Next();

    public Bitmap AdditionalBitmap() {
        return null;
    }

    public int getMark() {
        return mark;
    }

    public int getTime() {
        return time;
    }
}
