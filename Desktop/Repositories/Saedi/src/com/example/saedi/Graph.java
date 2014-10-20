package com.example.saedi;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class Graph extends Activity implements OnClickListener, OnTouchListener, OnDragListener {
	
	
	Button Menu;
	Button Eraser;
	Button Node;
	Button Link;
	Button Save;
	Button Write;
	Button Data;
	View ToDrag;
	RelativeLayout rlv;
	FrameLayout fl;
	
	int x_cord;
	int y_cord;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.draglayout);
	    
	    rlv = (RelativeLayout) findViewById(R.layout.graphingboard);
	    Menu = (Button) findViewById(R.id.button1Menus);
	    Eraser = (Button) findViewById(R.id.button6eraser);
	    Node = (Button) findViewById(R.id.button2NODE);
	    Link = (Button) findViewById(R.id.button4Link);
	    Save = (Button) findViewById(R.id.button7svsv);
	    Write = (Button) findViewById(R.id.button5Write);
	    Data = (Button) findViewById(R.id.button3Dta);
	    ToDrag = (View) findViewById(R.id.button1todraglikecrazy);
	    fl = (FrameLayout) findViewById(R.layout.draglayout);
	    
	    //Menu.setOnClickListener(this);
	    //Eraser.setOnClickListener(this);
	    //Node.setOnClickListener(this);
	    //Link.setOnClickListener(this);
	    //Save.setOnClickListener(this);
	    //Write.setOnClickListener(this);
	    //Data.setOnClickListener(this);
	    ToDrag.setOnTouchListener(this);
	    ToDrag.setOnDragListener(this);
	    //rlv.setOnDragListener(this);
	    
	    //Eraser.setVisibility(View.INVISIBLE);
	    //Node.setVisibility(View.INVISIBLE);
	    //Link.setVisibility(View.INVISIBLE);
	    //Save.setVisibility(View.INVISIBLE);
	    //Write.setVisibility(View.INVISIBLE);
	    //Data.setVisibility(View.INVISIBLE);
	    
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
	public boolean onDrag(View v, DragEvent event) {
		
		switch (event.getAction()) {
		case DragEvent.ACTION_DROP :
            x_cord = (int) event.getX();
            y_cord = (int) event.getY();
            ToDrag.setX(x_cord - (v.getWidth()/2));
            ToDrag.setY(y_cord - (v.getHeight()/2));
            ToDrag.invalidate();
            break;
		
		}

		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			ClipData data = ClipData.newPlainText("", "");
		    DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
			v.startDrag(data, shadowBuilder, v, 0);
			break;
		case MotionEvent.ACTION_UP:
			x_cord = (int) event.getX();
            y_cord = (int) event.getY();
            ToDrag.setX(x_cord - (v.getWidth()/2));
            ToDrag.setY(y_cord - (v.getHeight()/2));
            ToDrag.invalidate();
            break;
			
		}
		
		return false;
	}

	@Override
	public void onClick(View v) {
		
	}

}
