package edu.cosc4730.hackattack;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class MainActivity extends FragmentActivity {
	
	int turn;
	int p1moves, p1score, p1serverA, p1serverB;
	int p2moves, p2score, p2serverA, p2serverB;
	boolean p1stealA, p1stealB, p2stealA, p2stealB;
	int[] p1unitsA, p1unitsB, p2unitsA, p2unitsB;
	int[] p1remUnitsA, p1remUnitsB, p2remUnitsA, p2remUnitsB;
	boolean p1fullA, p1fullB, p2fullA, p2fullB;
	ArrayList<String> p1unitReport, p2unitReport;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
		BoardFragment boardFragment = new BoardFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		transaction.replace(R.id.fragment_container, boardFragment);
		transaction.addToBackStack(null);
		
		transaction.commit();
	}
	
	public void init() {
		turn = 1;
		p1moves = 0; p1score = 0;
		p2moves = 0; p2score = 0;
		p1stealA = false; p1stealB = false;
		p2stealA = false; p2stealB = false;
		p1unitsA = new int[]{0,0,0,0};
		p1unitsB = new int[]{0,0,0,0};
		p2unitsA = new int[]{0,0,0,0};
		p2unitsB = new int[]{0,0,0,0};
		p1remUnitsA = new int[4]; p1remUnitsB = new int[4];
		p2remUnitsA = new int[4]; p2remUnitsB = new int[4];
		p1fullA = false; p1fullB = false; p2fullA = false; p2fullB = false;
		p1unitReport = new ArrayList<String>();
		p2unitReport = new ArrayList<String>();
		initServer(1, 0); // 1 -> player 1, 0 -> Server A
		initServer(1, 1); // 1 -> player 1, 1 -> Server B
		initServer(2, 0); // 2 -> player 2, 0 -> Server A
		initServer(2, 1); // 2 -> player 2, 1 -> Server B
	}
	
	public void initServer(int turn, int server) {
		
		Random random = new Random();
		int encryptionLevel = random.nextInt(3)+2; 	// randomly pick 2, 3, or 4
		encryptionLevel *= 2; 						// multiply to be 4, 6, or 8
		
		// confirming encryptionLevels are appropriate (4, 6, or 8)
		Log.i("hackd", "p" + turn + "server" + server + " = " + encryptionLevel);
		
		/* now set encryptionLevel to appropriate Server */
		if(turn == 1) {
			if(server == 0) p1serverA = encryptionLevel;
			if(server == 1) p1serverB = encryptionLevel;
		}
		if(turn == 2) {
			if(server == 0) p2serverA = encryptionLevel;
			if(server == 1) p2serverB = encryptionLevel;
		}
	}
}


