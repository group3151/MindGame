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

    protected boolean haveAddittionalImage;//Показывает, есть ли дополнительное изображение
    protected ImageView additionalImage;//Дополнительное изображение (пример/последовательность фигур)
    protected int additionalImageCount;
    protected int additionalTime; //Время показа дополнительного изображения в МИЛЛИСЕКУНДАХ


    public abstract Bitmap getMainImage();


    public int getQuestionsCount() {
        return questionsCount;
    }


    public int getAdditionalTime() {
        return additionalTime;
    }


    public boolean isHaveAddittionalImage() {
        return haveAddittionalImage;
    }


    public int getAdditionalImageCount() {
        return additionalImageCount;
    }


    public abstract Bitmap getAdditionalImage();


    public int getMark() {
        return mark;
    }


    public int getTime() {
        return time;
    }


    public Level(ImageView mainImage) {
        this.mainImage = mainImage;
        haveAddittionalImage = false;
    }


    public abstract boolean TryClick(float x, float y);
    // return x >= x0 && x <= x1 && y >= y0 && y <= y1;
}
