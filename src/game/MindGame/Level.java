package game.MindGame;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by DVitinnik on 04-Nov-15.
 */
public abstract class Level {
    protected int time;//Время для выбора правильного варианта ответа в МИЛЛИСЕКУНДАХ
    protected int mark;//Оценка за правильный ответ
    protected int questionsCount;//Количество вопросов
    protected int x0, y0, x1, y1;//Координаты прямоугольника, в пределах которого надо нажать

    protected ImageView mainImage;//Основное изображение

    public abstract Bitmap getMainImage();


    public int getQuestionsCount() {
        return questionsCount;
    }

    public int getMark() {
        return mark;
    }

    public int getTime() {
        return time;
    }


    public Level(ImageView mainImage) {
        this.mainImage = mainImage;
    }

    public boolean TryClick(float x, float y) {
        return x >= x0 && x <= x1 && y >= y0 && y <= y1;
    }
}
