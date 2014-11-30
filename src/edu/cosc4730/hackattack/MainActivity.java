package edu.cosc4730.hackattack;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class MainActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		BoardFragment boardFragment = new BoardFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		transaction.replace(R.id.fragment_container, boardFragment);
		transaction.addToBackStack(null);
		
		transaction.commit();
	}
}
