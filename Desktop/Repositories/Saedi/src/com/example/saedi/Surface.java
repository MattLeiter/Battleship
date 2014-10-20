package com.example.saedi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.ImageView;

public class Surface extends ImageView {
	
	Point point = new Point(0, 0);

	public Surface(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}
