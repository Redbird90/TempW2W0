package com.example.jkt.wall2wall0.impl;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.example.jkt.wall2wall0.Graphics;
import com.example.jkt.wall2wall0.Graphics.PixmapFormat;
import com.example.jkt.wall2wall0.Pixmap;

public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    PixmapFormat format;
    Matrix matrix;
    int x;
    int y;
    Paint paint;
    
	public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
        this.matrix = new Matrix();
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.x = 0;
        this.y = 0;
    }
    
    public AndroidPixmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.format = PixmapFormat.RGB565;
        this.matrix = new Matrix();
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.x = 0;
        this.y = 0;
    }

    
    public int getWidth() {
        return bitmap.getWidth();
    }

    
    public int getHeight() {
        return bitmap.getHeight();
    }

    
    public PixmapFormat getFormat() {
        return format;
    }

    
    public void dispose() {
        bitmap.recycle();
    }
	
	public Bitmap getBitmap() {
		return bitmap;
	}

	
	public Bitmap getScaledBitmap(int width, int height) {
		Bitmap b = Bitmap.createScaledBitmap(bitmap, width, height, true);
		return b;
	}

	public Bitmap getGrayscaleBitmap() {
		Bitmap bitmap2 = bitmap;
		for(int i=0;i<bitmap2.getWidth();i++){
			for(int j=0;j<bitmap2.getHeight();j++){
				int pixel = bitmap2.getPixel(i, j);
				int red = Color.red(pixel);
				int blue = Color.blue(pixel);
				int green = Color.green(pixel);
				
				int gray = (red+green+blue)/3;
				bitmap2.setPixel(i, j, Color.rgb(gray, gray, gray));
			}
		}
		return bitmap2;
	}

	public void setOpacity(int alpha) {
		this.paint.setAlpha(alpha);
	}
	
	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}
	
	@Override
	public Pixmap scale(int width, int height) {
		return new AndroidPixmap(getScaledBitmap(width, height));
	}

	@Override
	public Pixmap rotate(int degree, int px, int py) {
		matrix.setRotate(degree, px, py);
		return this;
	}

	@Override
	public Pixmap toGrayscale() {
		Pixmap p = new AndroidPixmap(getGrayscaleBitmap());
		return p;
	}

	@Override
	public Matrix getMatrix() {
		return this.matrix;
	}

	@Override
	public void setMatrix(Matrix m) {
		this.matrix = m;
	}

	@Override
	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	@Override
	public Paint getPaint() {
		return this.paint;
	}

	@Override
	public void draw(Graphics graphics) {
		if(!bitmap.isRecycled())
			graphics.getCanvas().drawBitmap(bitmap, matrix, paint);
	}

	@Override
	public void draw(Graphics graphics, int x, int y) {
		if(!bitmap.isRecycled())
			graphics.getCanvas().drawBitmap(bitmap, this.x, this.y, this.paint);
	}

	@Override
	public void draw(Graphics graphics, Paint paint) {
		if(!bitmap.isRecycled())
			graphics.getCanvas().drawBitmap(bitmap, matrix, paint);
	}

	@Override
	public void draw(Graphics graphics, Matrix matrix, Paint paint) {
		if(!bitmap.isRecycled())
			graphics.getCanvas().drawBitmap(bitmap, matrix, paint);
	}

	@Override
	public Pixmap translate(int dx, int dy) {
		matrix.setTranslate(dx, dy);
		this.x = dx;
		this.y = dy;
		return this;
	}

	@Override
	public Pixmap postTranslate(int dx, int dy) {
		matrix.setTranslate(this.x+dx, this.y+dy);
		return this;
	}

	@Override
	public Pixmap rotate(float degree) {
		matrix.reset();
    	matrix.preRotate(degree, (bitmap.getWidth()/2)+x,(bitmap.getWidth()/2)+y);
    	matrix.preTranslate(x,y);
		return this;
	}

	@Override
	public boolean isSelected(int touchX, int touchY) {
		return (touchX > this.x && touchX < this.x + getWidth() && touchY > this.y && touchY < this.y + getHeight());
	}

	@Override
	public boolean isDisposed() {
		return bitmap.isRecycled();
	}

	@Override
	public Pixmap reset() {
		matrix.reset();
		matrix=new Matrix();
		return this;
	}
    
}
