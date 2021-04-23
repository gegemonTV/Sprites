package com.satanbakespancakes.sprites;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Pair;

public class Sprites {
    MySurfaceView testSurfaceView;
    Bitmap image;
    int counter = 0;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    float width, height, touchX = -1000, touchY = -1000, stepX = 4, stepY = 2;
    Pair<Float, Float> current;
    public Sprites(Bitmap img) {
        image = img;

    }

    public void draw(Canvas canvas){
        canvas.drawColor(Color.CYAN);
        if (counter >= 10) {
            counter = 0;
        }
        Bitmap bmp = Bitmap.createBitmap(image, image.getHeight() / 10 * counter, image.getHeight()/10*7, image.getWidth() / 10, image.getHeight() / 10);

        Matrix matrix = new Matrix();
        matrix.setTranslate(stepX * counter, stepY*counter);
        //matrix.setScale(3,3);
        canvas.drawBitmap(Bitmap.createScaledBitmap(bmp, image.getWidth() / 5, image.getHeight() / 5, true), matrix, paint);
    }

    public void setNext(){
        counter++;
    }
}