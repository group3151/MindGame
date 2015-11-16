package game.MindGame;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by DVitinnik on 04-Nov-15.
 */
public abstract class Level {
    protected boolean haveAddittionalBitmap;//Показывает, есть ли дополнительное изображение
    protected int time;//Время для выбора правильного варианта ответа в МИЛЛИСЕКУНДАХ
    protected int mark;//Оценка за правильный ответ
    protected int count;//Количество вопросов
    protected int x0, y0, x1, y1;//Координаты прямоугольника, в пределах которого надо нажать
    protected ImageView imageView;//Основное изображение

    protected ImageView additionalImage;//Дополнительное изображение (пример/последовательность фигур)
    protected int additionalTime; //Время показа дополнительного изображения в МИЛЛИСЕКУНДАХ

    public int getCount() {
        return count;
    }

    public int getAdditionalTime() {
        return additionalTime;
    }

    public boolean isHaveAddittionalBitmap() {
        return haveAddittionalBitmap;
    }

    public Level(ImageView imageView) {
        this.imageView = imageView;
        haveAddittionalBitmap = false;
    }

    public boolean TryClick(float x, float y) {
        return x >= x0 && x <= x1 && y >= y0 && y <= y1;
    }

    public abstract Bitmap Next();

    public abstract Bitmap getAdditionalBitmap();

    public int getMark() {
        return mark;
    }

    public int getTime() {
        return time;
    }
}
