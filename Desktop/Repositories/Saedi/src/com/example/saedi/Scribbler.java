package com.example.saedi;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.view.Display;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Scribbler extends Activity implements OnTouchListener, OnClickListener {
	
	ImageView Surface;
	Bitmap bitmap;
	Canvas canvas;
	Paint paint;
	float x1, y1, x2, y2;
	Point size = new Point(0,0);
	int saveCounter = 0;
	boolean visibilityCounter = false;
	String counter = "line";
	
	Button blue;
	Button red;
	Button grn;
	Button ylw;
	Button blk;
	Button wht;
	Button pnk;
	Button pur;
	Button save;
	Button erase;
	Button menu;
	ImageButton square;
	ImageButton circle;
	ImageButton triangle;
	ImageButton line;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.scribbleboard);
	    
	    blue = (Button) findViewById(R.id.buttonblue);
	    red = (Button) findViewById(R.id.buttonred);
	    grn = (Button) findViewById(R.id.buttongreen);
	    ylw = (Button) findViewById(R.id.buttonyellow);
	    blk = (Button) findViewById(R.id.buttonblack);
	    wht = (Button) findViewById(R.id.buttonwhite);
	    pnk = (Button) findViewById(R.id.buttonpink);
	    pur = (Button) findViewById(R.id.buttonpurple);
	    save = (Button) findViewById(R.id.buttonsave);
	    erase = (Button) findViewById(R.id.Buttoners);
	    menu = (Button) findViewById(R.id.buttonMENU);
	    circle = (ImageButton) findViewById(R.id.imageButtoncirc);
	    square = (ImageButton) findViewById(R.id.imageButtonsquare);
	    triangle = (ImageButton) findViewById(R.id.imageButtontriangle);
	    line = (ImageButton) findViewById(R.id.imageButtonline);
	    
	    blue.setOnClickListener(this);
	    red.setOnClickListener(this);
	    grn.setOnClickListener(this);
	    ylw.setOnClickListener(this);
	    blk.setOnClickListener(this);
	    wht.setOnClickListener(this);
	    pnk.setOnClickListener(this);
	    pur.setOnClickListener(this);
	    save.setOnClickListener(this);
	    erase.setOnClickListener(this);
	    menu.setOnClickListener(this);
	    circle.setOnClickListener(this);
	    square.setOnClickListener(this);
	    triangle.setOnClickListener(this);
	    line.setOnClickListener(this);
	    
	    blue.setVisibility(View.INVISIBLE);
	    red.setVisibility(View.INVISIBLE);
	    grn.setVisibility(View.INVISIBLE);
	    ylw.setVisibility(View.INVISIBLE);
	    blk.setVisibility(View.INVISIBLE);
	    wht.setVisibility(View.INVISIBLE);
	    pnk.setVisibility(View.INVISIBLE);
	    pur.setVisibility(View.INVISIBLE);
	    save.setVisibility(View.INVISIBLE);
	    erase.setVisibility(View.INVISIBLE);
	    circle.setVisibility(View.INVISIBLE);
	    square.setVisibility(View.INVISIBLE);
	    triangle.setVisibility(View.INVISIBLE);
	    line.setVisibility(View.INVISIBLE);
	    
	    Surface = (ImageView) this.findViewById(R.id.imageView1);

	    Display currentDisplay = getWindowManager().getDefaultDisplay();
	    currentDisplay.getSize(size);

	    bitmap = Bitmap.createBitmap((int) size.x, (int) size.y,
	        Bitmap.Config.ARGB_8888);
	    Surface.setDrawingCacheEnabled(true);
	    canvas = new Canvas(bitmap);
	    paint = new Paint();
	    paint.setColor(Color.BLACK);     
	    paint.setStrokeWidth(10);
	    Surface.setImageBitmap(bitmap);
	    Surface.setOnTouchListener(this);
	    canvas.drawColor(Color.WHITE);
	
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		if (counter.equals("circle")) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				x1 = event.getX();
				y1 = event.getY();
				break;
			case MotionEvent.ACTION_UP:
				float rad = (float) Math.sqrt((Math.abs(x1 - event.getX()) * Math.abs(x1 - event.getX()))
						+ (Math.abs(y1 - event.getY()) * Math.abs(y1 - event.getY())));
		    	canvas.drawCircle(x1, y1, rad, paint);
		    	Surface.invalidate();
		    	break;
			}
		}
		if (counter.equals("triangle")) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
		    	break;
			}
		}
		if (counter.equals("square")) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				x1 = event.getX();
				y1 = event.getY();
		    	break;
			case MotionEvent.ACTION_UP:
				canvas.drawRect(x1, y1, 
		    			event.getX(), event.getY(), paint);
		    	Surface.invalidate();
				break;
			}
		}
		if (counter.equals("line")) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
		    	x1 = event.getX();
		    	y1 = event.getY();
		    	break;
		    case MotionEvent.ACTION_MOVE:
		    	x2 = event.getX();
		    	y2 = event.getY();
		    	canvas.drawLine(x1, y1, x2, y2, paint);
		    	Surface.invalidate();
		    	x1 = x2;
		    	y1 = y2;
		    	break;
		    case MotionEvent.ACTION_UP:
		    	x2 = event.getX();
		    	y2 = event.getY();
		    	canvas.drawLine(x1, y1, x2, y2, paint);
		    	Surface.invalidate();
		    	break;
			}
			
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		
		
		
		switch (v.getId()) {
		case R.id.buttonMENU:
			
			if (visibilityCounter == false) {
				blue.setVisibility(View.VISIBLE);
			    red.setVisibility(View.VISIBLE);
			    grn.setVisibility(View.VISIBLE);
			    ylw.setVisibility(View.VISIBLE);
			    blk.setVisibility(View.VISIBLE);
			    wht.setVisibility(View.VISIBLE);
			    pnk.setVisibility(View.VISIBLE);
			    pur.setVisibility(View.VISIBLE);
			    save.setVisibility(View.VISIBLE);
			    erase.setVisibility(View.VISIBLE);
			    circle.setVisibility(View.VISIBLE);
			    square.setVisibility(View.VISIBLE);
			    triangle.setVisibility(View.VISIBLE);
			    line.setVisibility(View.VISIBLE);
			    visibilityCounter = true;
		    	break;
				
			}
			else {
				blue.setVisibility(View.INVISIBLE);
			    red.setVisibility(View.INVISIBLE);
			    grn.setVisibility(View.INVISIBLE);
			    ylw.setVisibility(View.INVISIBLE);
			    blk.setVisibility(View.INVISIBLE);
			    wht.setVisibility(View.INVISIBLE);
			    pnk.setVisibility(View.INVISIBLE);
			    pur.setVisibility(View.INVISIBLE);
			    save.setVisibility(View.INVISIBLE);
			    erase.setVisibility(View.INVISIBLE);
			    circle.setVisibility(View.INVISIBLE);
			    square.setVisibility(View.INVISIBLE);
			    triangle.setVisibility(View.INVISIBLE);
			    line.setVisibility(View.INVISIBLE);
			    visibilityCounter = false;
			    break;
				
			}	
		case R.id.buttonblue:
			paint.setColor(Color.BLUE);
			break;
		case R.id.buttonred:
			paint.setColor(Color.RED);
			break;
		case R.id.buttongreen:
			paint.setColor(Color.GREEN);
			break;
		case R.id.buttonyellow:
			paint.setColor(Color.YELLOW);
			break;
		case R.id.buttonblack:
			paint.setColor(Color.BLACK);
			break;
		case R.id.buttonwhite:
			paint.setColor(Color.WHITE);
			break;
		case R.id.buttonpink:
			paint.setColor(Color.parseColor("#FF00FF"));
			break;
		case R.id.buttonpurple:
			paint.setColor(Color.parseColor("#800080"));
			break;
		case R.id.Buttoners:
			canvas.drawColor(Color.WHITE);
			Surface.invalidate();
			break;
		case R.id.buttonsave:

			try {
	           
	            Bitmap bitmap2 = Surface.getDrawingCache();

	            String extr = Environment.getExternalStorageDirectory().toString();
	            File mFolder = new File(extr + "/Download/");
	            if (!mFolder.exists()) {
	                mFolder.mkdir();
	            }

	            String s = String.format("tmpscr%d.png", saveCounter);

	            File f = new File(mFolder.getAbsolutePath(), s);

	            FileOutputStream fos = null;
	            fos = new FileOutputStream(f);
	            bitmap2.compress(CompressFormat.JPEG, 100, fos);
	            fos.flush();
	            fos.close();

	            saveCounter++;

	            Toast.makeText(getApplicationContext(), "image saved to Downloads", 5000).show();
	        } catch (Exception e) {
	        	Toast.makeText(getApplicationContext(), "Failed To Save", 5000).show();
	        }
			break;
		case R.id.imageButtoncirc:
			counter = "circle";
			break;
		case R.id.imageButtonsquare:
			counter = "square";
			break;
		case R.id.imageButtontriangle:
			counter = "triangle";
			break;
		case R.id.imageButtonline:
			counter = "line";
			break;
		}
		
	}

}
