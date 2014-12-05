package edu.cosc4730.hackattack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UnitView extends View {
	
	public Context myContext;
	
	int[] units;
	Bitmap test;
	float scale;
	int owner;		// 0 -> player, 1 -> opponent

	public UnitView(Context context) {
		super(context);
		myContext = context;
		init();
	}
	
	public UnitView(Context context, AttributeSet attrs) {
		super(context);
		myContext = context;
		init();
	}
	
	public UnitView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		myContext = context;
		init();
	}

	public void init() {
		
		test = BitmapFactory.decodeResource(getResources(), R.drawable.opp_unit_2);
		scale = getResources().getDisplayMetrics().density;
		owner = 0;
	}
	
	public void setUnits(/*int owner/*, int[] units*/) {
		Log.i("hackd", "made it inside setUnits");
		//this.owner = owner;
		//this.units = units;
	}
	
	public void test(){}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
		View myView = null;
		return myView;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		int xIndent = 0;
		//canvas.drawColor(Color.GRAY);
		//canvas.drawBitmap(test, 0, 0, null);
		/*
		for(int i = 0; i<4; i++) {
			if(units[i] == 4) {
				if(owner == 0) {
					canvas.drawBitmap(test, xIndent, 0, null);
					xIndent += 55; xIndent *= scale;
				}
				if(owner == 1) {
					canvas.drawBitmap(test, xIndent, 0, null);
					xIndent += 55; xIndent *= scale;
				}
			}
			if(units[i] == 2) {
				if(owner == 0) {
					canvas.drawBitmap(test, xIndent, 0, null);
					xIndent += 55; xIndent *= scale;
				}
				if(owner == 1) {
					canvas.drawBitmap(test, xIndent, 0, null);
					xIndent += 55; xIndent *= scale;
				}
			}
		}
		*/
	}
}
