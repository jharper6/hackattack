package edu.cosc4730.hackattack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UnitView extends View {
	
	public Context myContext;
	
	Bitmap test;
	float scale;

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
		super(context);
		myContext = context;
		init();
	}
	
	public void init() {
		
		test = BitmapFactory.decodeResource(getResources(), R.drawable.test);
		scale = getResources().getDisplayMetrics().density;
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
		View myView = null;
		return myView;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		
		canvas.drawColor(Color.GRAY);
		canvas.drawBitmap(test, 0, 0, null);
	}
}
