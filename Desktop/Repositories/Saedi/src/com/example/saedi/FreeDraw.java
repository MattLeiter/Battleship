package com.example.saedi;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.example.saedi.R.id;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.text.method.TextKeyListener;
import android.view.Display;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class FreeDraw extends Activity implements OnTouchListener, OnClickListener {
	
	ArrayList<Node> Nodes = new ArrayList<Node>();
	ArrayList<Link> Links = new ArrayList<Link>();
	
	ImageView Surface;
	Bitmap bitmap;
	Canvas canvas;
	Paint paint;
	float x1, y1, x2, y2;
	String Counter = "Write";
	int saveCounter = 0;
	String NodeName = "";
	int nodeCounter = 0;
	boolean viscounter = false;
	
	Point size = new Point(0,0);
	
	Button Line;
	Button Circle;
	Button Text;
	Button Erase;
	Button Save;
	Button Write;
	Button data;
	Button Menu;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.drawingboard);
	    
	    Line = (Button) findViewById(R.id.buttonLink);
	    Circle = (Button) findViewById(R.id.buttonCirc);
	    Text = (Button) findViewById(R.id.buttonText);
	    Erase = (Button) findViewById(R.id.buttonErase);
	    Save = (Button) findViewById(R.id.buttonSave);
	    Write = (Button) findViewById(R.id.buttonWrite);
	    data = (Button) findViewById(R.id.buttonData);
	    Menu = (Button) findViewById(R.id.buttonMMENU);
	    
	    Line.setOnClickListener(this);
	    Circle.setOnClickListener(this);
	    Text.setOnClickListener(this);
	    Erase.setOnClickListener(this);
	    Save.setOnClickListener(this);
	    Write.setOnClickListener(this);
	    data.setOnClickListener(this);
	    Menu.setOnClickListener(this);
	    
	    Line.setVisibility(View.INVISIBLE);
	    Circle.setVisibility(View.INVISIBLE);
	    Text.setVisibility(View.INVISIBLE);
	    Erase.setVisibility(View.INVISIBLE);
	    Save.setVisibility(View.INVISIBLE);
	    Write.setVisibility(View.INVISIBLE);
	    data.setVisibility(View.INVISIBLE);

	    Surface = (ImageView) this.findViewById(R.id.img);

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
	
	protected void onDraw(Canvas c) {
		
	}
	
	private boolean inProximity(float x, float y) {
		
		for (int i = 0; i < Nodes.size(); i++) {
			if ((Nodes.get(i).getX() - 47) < x && x < (Nodes.get(i).getX() + 47) 
					&& (Nodes.get(i).getY() - 47) < y && y < (Nodes.get(i).getY() + 47)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean nodeProximity(float x, float y) {
		
		for (int i = 0; i < Nodes.size(); i++) {
			if ((Nodes.get(i).getX() - 160) < x && x < (Nodes.get(i).getX() + 160) 
					&& (Nodes.get(i).getY() - 160) < y && y < (Nodes.get(i).getY() + 160)) {
				return true;
			}
		}
		return false;	
	}
	
	private Node inProximityOf(float x, float y) {
		
		for (int i = 0; i < Nodes.size(); i++) {
			if ((Nodes.get(i).getX() - 50) < x && x < (Nodes.get(i).getX() + 50) 
					&& (Nodes.get(i).getY() - 50) < y && y < (Nodes.get(i).getY() + 50)) {
				return Nodes.get(i);
			}
		}
		return null;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		if (Counter.equals("Line")) {
			switch (event.getAction()) {
		    case MotionEvent.ACTION_DOWN:
		    	x1 = event.getX();
		    	y1 = event.getY();
		    	canvas.drawCircle(x1, y1, 20, paint);
		    	Surface.invalidate();
		    	break;
		    case MotionEvent.ACTION_MOVE:
		    	break;
		    case MotionEvent.ACTION_UP:
		    	x2 = event.getX();
		    	y2 = event.getY();
		    	if (inProximity(x1, y1) && inProximity(x2, y2)) {
		    		canvas.drawLine(x1, y1, x2, y2, paint);
			    	canvas.drawCircle(x2, y2, 20, paint);
			    	Surface.invalidate();
			    	Links.add(new Link(x1, y1, x2, y2, inProximityOf(x1, y1), 
			    			inProximityOf(x2, y2)));
		    	}
		    	else {
		    		if (inProximity(x1, y1)) {
		    			paint.setColor(Color.BLUE);
		    		}
		    		else {
		    			paint.setColor(Color.WHITE);
		    		}
		    		canvas.drawCircle(x1, y1, 20, paint);
			    	Surface.invalidate();
			    	paint.setColor(Color.BLACK);
		    		Toast.makeText(getApplicationContext(),
							"Invalid Link", Toast.LENGTH_SHORT).show();
		    	}
		    	break;
		    }	
		}
		
		if (Counter.equals("Circle")) {
			switch (event.getAction()) {
		    case MotionEvent.ACTION_DOWN:
		    	x1 = event.getX();
		    	y1 = event.getY();
		    	
		    	if (nodeProximity(x1, y1)) {
		    		Toast.makeText(getApplicationContext(),
							"Invalid Node", Toast.LENGTH_SHORT).show();
		    		break;
		    	}
		    	else {
		    		
		    		if (NodeName.equals("None") || NodeName.equals("")) {
		    			canvas.drawCircle(x1, y1, 90, paint);
			    		paint.setColor(Color.BLACK);
			    		paint.setTextSize(30);
			    		String ndtxt = String.format("Node %d", nodeCounter);
			    		canvas.drawText(ndtxt, x1, y1, paint);
			    		paint.setColor(Color.BLUE);
			    		paint.setAlpha(125);
						Surface.invalidate();
						Nodes.add(new Node(x1, y1, ndtxt));
						nodeCounter++;
						break;   			
		    		}
		    		else {
		    			canvas.drawCircle(x1, y1, 90, paint);
			    		paint.setColor(Color.BLACK);
			    		paint.setTextSize(30);
			    		canvas.drawText(NodeName, x1, y1, paint);
			    		paint.setColor(Color.BLUE);
			    		paint.setAlpha(125);
						Surface.invalidate();
						Nodes.add(new Node(x1, y1, NodeName));
						nodeCounter++;
						NodeName = "";
						break;	
		    			
		    		}
		    			
		    	}	
		    }	
		}
		
		if (Counter.equals("Write")) {
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
			
			}
		}
		
		if (Counter.equals("Text")) {
			
		}
		
		return true;
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.buttonLink:
			Counter = "Line";
			paint.setColor(Color.BLACK);     
		    paint.setStrokeWidth(10);
			break;
		case R.id.buttonCirc:
			Counter = "Circle";
			paint.setColor(Color.BLUE);     
		    paint.setStrokeWidth(10);
			paint.setAlpha(125);
			break;
		case R.id.buttonWrite:
			Counter = "Write";
			paint.setColor(Color.RED);     
		    paint.setStrokeWidth(10);
		    break;
		case R.id.buttonText:
			Counter = "Text";
			final Dialog dialog = new Dialog(this);
			dialog.setContentView(R.layout.custom_dialog);
			dialog.setTitle("Node Name");
 
			Button OkButton = (Button) dialog.findViewById(R.id.buttonOk);
			Button CnButton = (Button) dialog.findViewById(R.id.buttonCancel);
			final EditText text = (EditText) dialog.findViewById(R.id.editText1);
			OkButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					NodeName = text.getText().toString();
					dialog.dismiss();
				}
			});
			CnButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					NodeName = "None";
					dialog.dismiss();
				}
			});
 
			dialog.show();
			break;
		case R.id.buttonErase:
	    	canvas.drawColor(Color.WHITE);
	    	Nodes.clear();
	    	Links.clear();
	    	NodeName = "";
	    	nodeCounter = 0;
	    	Surface.invalidate();
			break;
		case R.id.buttonData:
			Counter = "Data";
			final Dialog dialog2 = new Dialog(this);
			dialog2.setContentView(R.layout.custom_dialog_2);
			dialog2.setTitle("Data");
			TextView nodeNum = (TextView) dialog2.findViewById(id.textViewNodeNum);
			TextView linkNum = (TextView) dialog2.findViewById(id.textViewLinkNum);
			LinearLayout scroll = (LinearLayout) dialog2.findViewById(R.id.scrollln);
			for (int i = 0; i < Links.size(); i++) {
				TextView txt = new TextView(scroll.getContext());
				LayoutParams lparams = new LayoutParams(
						   LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				txt.setLayoutParams(lparams);
				txt.setTextSize(15);
				txt.setTextColor(Color.WHITE);
				txt.setText(String.format("Nodes %s and %s are connected",
						Links.get(i).getFirst().getName(), Links.get(i).getSecond().getName()));
				scroll.addView(txt);	
			}
			
			
			nodeNum.setText(String.format("Number of Nodes:        %d" , Nodes.size()));
			linkNum.setText(String.format("Number of Links:        %d" , Links.size()));
 
			Button ExitButton = (Button) dialog2.findViewById(R.id.buttonExit);
			ExitButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog2.dismiss();
				}
			});
			dialog2.show();
			break;
			
		case R.id.buttonSave:
			final Bitmap myRoundedImage = Surface.getDrawingCache();
			try {
	           
	            Bitmap bitmap2 = Surface.getDrawingCache();

	            String extr = Environment.getExternalStorageDirectory().toString();
	            File mFolder = new File(extr + "/Download/");
	            if (!mFolder.exists()) {
	                mFolder.mkdir();
	            }

	            String s = String.format("tmp%d.png", saveCounter);

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
		case R.id.buttonMMENU:
			if (viscounter == false) {
				Line.setVisibility(View.VISIBLE);
			    Circle.setVisibility(View.VISIBLE);
			    Text.setVisibility(View.VISIBLE);
			    Erase.setVisibility(View.VISIBLE);
			    Save.setVisibility(View.VISIBLE);
			    Write.setVisibility(View.VISIBLE);
			    data.setVisibility(View.VISIBLE);
			    viscounter = true;
				
			}
			else {
				Line.setVisibility(View.INVISIBLE);
			    Circle.setVisibility(View.INVISIBLE);
			    Text.setVisibility(View.INVISIBLE);
			    Erase.setVisibility(View.INVISIBLE);
			    Save.setVisibility(View.INVISIBLE);
			    Write.setVisibility(View.INVISIBLE);
			    data.setVisibility(View.INVISIBLE);
			    viscounter = false;
				
			}
			
			break;
	    }

		}
		
	}

