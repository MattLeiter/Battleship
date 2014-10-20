package com.example.saedi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewPropertyAnimator;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	Button freeDrw;
	Button Scribble;
	Button Chart;
	View circ;
	View circ1;
	View circ2;
	Thread animator;
	ViewPropertyAnimator v1; 
	ViewPropertyAnimator v2; 
	ViewPropertyAnimator v3; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		freeDrw = (Button) findViewById(R.id.FreeButton);
		Scribble = (Button) findViewById(R.id.ButtonScribble);
		Chart = (Button) findViewById(R.id.ButtonChart);
		freeDrw.setOnClickListener(this);
		Scribble.setOnClickListener(this);
		Chart.setOnClickListener(this);
		circ = (View) findViewById(R.id.view1);
		circ1 = (View) findViewById(R.id.View01);
		circ2 = (View) findViewById(R.id.View02);
		
		v2 = circ.animate();
		v1 = circ1.animate();
		v3 = circ2.animate();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
			case R.id.FreeButton:
				Intent intent = new Intent(v.getContext(), FreeDraw.class);
				startActivity(intent);
				break;
			case R.id.ButtonScribble:
				Intent intent2 = new Intent(v.getContext(), Scribbler.class);
				startActivity(intent2);
				break;
			case R.id.ButtonChart:
				Intent intent3 = new Intent(v.getContext(), Graph.class);
				startActivity(intent3);
				break;
				
		
		}
		
	}

}
