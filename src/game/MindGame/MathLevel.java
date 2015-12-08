package game.MindGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by Инна on 16.11.2015.
 */
public class MathLevel extends Level {

    String task;
    private int radius;
    private Paint paint;
    private int[][] points;
    final private int N = 8;

    public MathLevel(ImageView imageView) {
        super(imageView);
        mark = 100;
        time = 3000;
        questionsCount = 10;

        task = new String();
        paint = new Paint();
        points = new int[N][2];
        radius = 50;

    }

    @Override
    public Bitmap getMainImage() {
        int k = 21;
        Bitmap bitmap = Bitmap.createBitmap(mainImage.getWidth(), mainImage.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Random rand = new Random(System.currentTimeMillis());
        int res = Exercise(k, rand);
        int indexRes = java.lang.Math.abs(rand.nextInt() % N);
        showExercise(canvas);
        initPoints(mainImage.getWidth(), mainImage.getHeight());
        showAnswers(canvas, rand, k, res, indexRes);

        x1 = points[indexRes][0] + radius;
        y1 = points[indexRes][1] + radius;

        x0 = points[indexRes][0] - radius;
        y0 = points[indexRes][1] - radius;

        return bitmap;
    }

    private void showAnswers(Canvas canvas, Random rand, int k, int res, int indexRes) {
        paint.setColor(Color.parseColor("#ffa447a5"));
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < N; i++) {
            canvas.drawCircle(points[i][0], points[i][1], radius, paint);
        }
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < N; i++) {
            canvas.drawCircle(points[i][0], points[i][1], radius, paint);
        }
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(String.valueOf(res), points[indexRes][0] - radius / 2, points[indexRes][1] + radius / 3, paint);
        fillingButtons(canvas, res, rand, indexRes, k);
    }

    private void fillingButtons(Canvas canvas, int res, Random rand, int index, int k) {
        for (int i = 0; i < N; i++) {
            int r = rand.nextInt() % (k * 2);
            while (r == res) {
                r = rand.nextInt() % (k * 2);
            }
            if (i != index) {
                canvas.drawText(String.valueOf(r), points[i][0] - radius / 2, points[i][1] + radius / 3, paint);
            }
        }
    }

    private void initPoints(int w, int h) {
        points[0][0] = points[3][0] = points[6][0] = w / 6;
        points[1][0] = points[4][0] = points[7][0] = (5 * w) / 6;
        points[2][0] = points[5][0] = w / 2;

        points[0][1] = points[1][1] = h / 4;
        points[2][1] = (5 * h) / 12 + 15;
        points[3][1] = points[4][1] = (7 * h) / 12;
        points[5][1] = (3 * h) / 4;
        points[6][1] = points[7][1] = (11 * h) / 12;
    }

    private int Exercise(int k, Random random) {
        int a, b, r;
        char[] operation = {'+', '-', '*', '/'};
        char op = operation[java.lang.Math.abs(random.nextInt() % operation.length)];

        if (op == '+') {
            a = random.nextInt() % k;
            b = java.lang.Math.abs(random.nextInt() % k);
            r = a + b;
        } else if (op == '-') {
            a = random.nextInt() % k;
            b = java.lang.Math.abs(random.nextInt() % k);
            r = a - b;
        } else if (op == '*') {
            a = random.nextInt() % (k / 2);
            b = java.lang.Math.abs(random.nextInt() % (k / 2));
            r = a * b;

        } else {
            b = java.lang.Math.abs(random.nextInt() % (k / 2));
            if (b == 0) b = 1;
            a = java.lang.Math.abs(random.nextInt() % (k / 2) * b);
            r = a / b;
        }
        task = (String.valueOf(a).concat(String.valueOf(op)).concat(String.valueOf(b)));
        return r;
    }

    private void showExercise(Canvas canvas) {
        paint.setColor(Color.parseColor("#ffa447a5"));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawRect(mainImage.getWidth() / 4, 20, mainImage.getWidth() * 3 / 4, mainImage.getHeight() / 6, paint);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(mainImage.getWidth() / 4, 20, mainImage.getWidth() * 3 / 4, mainImage.getHeight() / 6, paint);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
        canvas.drawText(task, mainImage.getWidth() * 3 / 8, mainImage.getHeight() / 8, paint);
    }
}