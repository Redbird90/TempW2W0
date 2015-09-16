package com.example.jkt.wall2wall0.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;

import com.example.jkt.wall2wall0.Graphics;
import com.example.jkt.wall2wall0.Image;
import com.example.jkt.wall2wall0.Pixmap;

public class AndroidGraphics implements Graphics {
    AssetManager assets;
    Bitmap frameBuffer;
    Canvas canvas;
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }

/*    @Override
    public Image newImage(String fileName, ImageFormat format) {
        Config config = null;
        if (format == ImageFormat.RGB565)
            config = Config.RGB_565;
        else if (format == ImageFormat.ARGB4444)
            config = Config.ARGB_4444;
        else
            config = Config.ARGB_8888;

        Options options = new Options();
        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap = null;



        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                        + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }
        }

        if (bitmap.getConfig() == Config.RGB_565)
            format = ImageFormat.RGB565;
        else if (bitmap.getConfig() == Config.ARGB_4444)
            format = ImageFormat.ARGB4444;
        else
            format = ImageFormat.ARGB8888;

        return new AndroidImage(bitmap, format);
    }*/

    @Override
    public Image newImage(String fileName, ImageFormat format) {

        Bitmap.Config config = null;
        if (format == Graphics.ImageFormat.RGB565)
            config = Bitmap.Config.RGB_565;
        else if (format == Graphics.ImageFormat.ARGB4444)
            config = Bitmap.Config.ARGB_4444;
        else
            config = Bitmap.Config.ARGB_8888;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap = null;



        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                        + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }
        }

        if (bitmap.getConfig() == Bitmap.Config.RGB_565)
            format = Graphics.ImageFormat.RGB565;
        else if (bitmap.getConfig() == Bitmap.Config.ARGB_4444)
            format = Graphics.ImageFormat.ARGB4444;
        else
            format = Graphics.ImageFormat.ARGB8888;

        return new AndroidImage(bitmap, format);



        // Attempt to transfer this method's work to an AsyncTask
/*        AssetWorkerTask assetWorkerTask = new AssetWorkerTask();
        assetWorkerTask.execute(fileName, format);
        try {
            AndroidImage img = assetWorkerTask.get();
        } catch (InterruptedException e) {
            Log.e("AndroidGraphics", String.valueOf(e));
        } catch (ExecutionException e) {
            Log.e("AndroidGraphics", String.valueOf(e));
        }

        return img;*/

    }

    @Override
    public void clearScreen(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
                (color & 0xff));
    }

    @Override
    public Pixmap newPixmap(String fileName, PixmapFormat format) {
        return null;
    }

    @Override
    public Pixmap newPixmap(Bitmap bitmap) {
        return null;
    }

    @Override
    public void clear(int color) {

    }

    @Override
    public void drawPixel(int x, int y, int color) {

    }

    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {

    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y) {

    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, float sx, float sy, float degrees, float px, float py) {

    }

    @Override
    public void drawPixel(int x, int y, int color, Paint paint) {

    }

    @Override
    public void drawLine(int x, int y, int x2, int y2, int color, Paint paint) {

    }

    @Override
    public void drawRect(int x, int y, int width, int height, Paint paint) {

    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight, Paint paint) {

    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, Paint paint) {

    }

    @Override
    public void drawPixmap(Pixmap pixmap) {

    }

    @Override
    public void drawPixmap(Pixmap pixmap, Paint paint) {

    }

    @Override
    public void drawText(String text, int x, int y, Paint paint) {

    }

    @Override
    public void drawARGB(int a, int r, int g, int b) {
        paint.setStyle(Style.FILL);
        canvas.drawARGB(a, r, g, b);
    }

    @Override
    public void drawString(String text, int x, int y, Paint paint) {
        canvas.drawText(text, x, y, paint);
    }

    @Override
    public void drawImage(Image image, int x, int y, int srcX, int srcY,
                          int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth;
        dstRect.bottom = y + srcHeight;

        canvas.drawBitmap(((AndroidImage) image).bitmap, srcRect, dstRect, null);
    }

    @Override
    public void drawImage(Image image, int x, int y) {
        if (image == null) {
            Log.i("AndroidGraphics", "Image is NULL");
        }
        canvas.drawBitmap(((AndroidImage) image).bitmap, x, y, null);
    }

    public void drawScaledImage(Image image, int x, int y, int width,
                                int height, int srcX, int srcY, int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + width;
        dstRect.bottom = y + height;

        canvas.drawBitmap(((AndroidImage) image).bitmap, srcRect, dstRect, null);
    }

    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

}
