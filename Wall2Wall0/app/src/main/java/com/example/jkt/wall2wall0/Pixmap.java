package com.example.jkt.wall2wall0;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.example.jkt.wall2wall0.Graphics.PixmapFormat;

public interface Pixmap {
	
	public int getX();
	
	public int getY();
    
	public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
    
    public boolean isDisposed();
    
    public Bitmap getScaledBitmap(int width, int height);
    
    public Bitmap getBitmap();
    
    public Bitmap getGrayscaleBitmap();
    
    public void setOpacity(int alpha);
    
    public void draw(Graphics graphics);
    
    public void draw(Graphics graphics, int x, int y);
    
    public void draw(Graphics graphics, Paint p);
    
    public void draw(Graphics graphics, Matrix m, Paint p);
    
    public Pixmap translate(int dx, int dy);
    
    public Pixmap scale(int width, int height);
    
    public Pixmap rotate(int degree, int px, int py);
    
    public Pixmap rotate(float degree);
    
    public Pixmap toGrayscale();
    
    public Matrix getMatrix();
    
    public void setMatrix(Matrix m);
    
    public void setPaint(Paint paint);
    
    public Paint getPaint();
    
    public Pixmap postTranslate(int dx, int dy);
    
    public boolean isSelected(int touchX, int touchY);
    
    public Pixmap reset();
    
}
