package com.satanbakespancakes.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    final String TAG ="SPRITES";
    SurfaceThread thread;
    boolean touchevent = false;
    Sprites sprite;
    Paint paint;
    float width, height, currentX, currentY, touchX = -1000, touchY = -1000, stepX = 0, stepY = 0;
    Bitmap img, sprite_image;


    public MySurfaceView(Context context) {
        super(context);

        getHolder().addCallback(this);
        img = BitmapFactory.decodeResource(getResources(), R.drawable.cleric_sprites);
        sprite = new Sprites(img);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        thread = new SurfaceThread(getHolder(), this);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;
        thread.running = false;
        while (retry){
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        event.getX();
        event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touchevent = true;
        }

        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        width = canvas.getWidth();
        height = canvas.getHeight();
        sprite.draw(canvas);
        sprite.setNext();
        //canvas.drawBitmap(img, 0,0, paint);
    }

    class SurfaceThread extends Thread{

        boolean running = true;
        SurfaceHolder holder;
        MySurfaceView view1;
        Paint paint = new Paint();

        public SurfaceThread(SurfaceHolder holder, MySurfaceView surfaceView) {
            view1 = surfaceView;
            this.holder = holder;
        }

        @Override
        public void run() {
            super.run();
            while (running){
                Canvas canvas = null;
                canvas = holder.lockCanvas();
                synchronized (holder) {
                    if (canvas != null) draw(canvas);
                }
                if (canvas != null) { holder.unlockCanvasAndPost(canvas);}
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
