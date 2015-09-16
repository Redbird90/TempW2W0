package com.example.jkt.wall2wall0.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidLowRenderView extends SurfaceView implements Runnable {
    AndroidGame game;
    Bitmap framebuffer;
    Thread renderThread = null;
    SurfaceHolder holder;
    volatile boolean running = false;
    
    public AndroidLowRenderView(AndroidGame game, Bitmap framebuffer) {
        super(game);
        this.game = game;
        this.framebuffer = framebuffer;
        this.holder = getHolder();
    }

    public void resume() { 
        running = true;
        renderThread = new Thread(this);
        renderThread.start();         
    }      
    
    public void run() {
        Rect dstRect = new Rect();
        while(running) {  
            if(!holder.getSurface().isValid())
                continue;           
            
            Canvas canvas = null;
            try{
            	canvas = holder.lockCanvas();
                canvas.getClipBounds(dstRect);
                canvas.drawBitmap(framebuffer, null, dstRect, null);
            }finally{
            	if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void pause() {                        
        running = false;                        
        while(true) {
            try {
                renderThread.join();
                break;
            } catch (InterruptedException e) {
                // retry
            }
        }
    }        
}