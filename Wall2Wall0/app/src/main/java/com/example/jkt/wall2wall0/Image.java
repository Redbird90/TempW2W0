package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.Graphics.ImageFormat;

public interface Image {
	public int getWidth();

	public int getHeight();

	public ImageFormat getFormat();

	public void dispose();

}
