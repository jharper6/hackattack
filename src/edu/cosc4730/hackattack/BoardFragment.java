package edu.cosc4730.hackattack;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BoardFragment extends Fragment {
	
	MainActivity game;
	//Button prob1, prob2;
	Button btnLaunch4, btnLaunch2, btnSteal, btnEndTurn, btnReset;
	//UnitView oppUnitsA, oppUnitsB, playerUnitsA, playerUnitsB;
	ImageView iv_oppA1, iv_oppA2, iv_oppA3, iv_oppA4;
	ImageView iv_oppB1, iv_oppB2, iv_oppB3, iv_oppB4;
	ImageView iv_playerA1, iv_playerA2, iv_playerA3, iv_playerA4;
	ImageView iv_playerB1, iv_playerB2, iv_playerB3, iv_playerB4;
	ImageView iv_serverA, iv_serverB;
	TextView tv_turn, tv_moves, tv_p1score, tv_p2score;
	Bitmap playerUnit2, playerUnit4, oppUnit2, oppUnit4;
	Bitmap serverA4, serverA6, serverA8, serverB4, serverB6, serverB8;
	
	//LayoutInflater inflater; ViewGroup container; Bundle savedInstanceState;
	Context myContext;
	
	public BoardFragment() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		
		/* the instance of MainActivity we'll reference for game information (ie turn, score, etc) */
		game = (MainActivity) getActivity();
		myContext = game.getApplicationContext();
        View myView = inflater.inflate(R.layout.fragment_board, container, false);
        
        /* the ImageViews that represent the Servers */
        serverA4 = BitmapFactory.decodeResource(getResources(), R.drawable.servera4);
        serverA6 = BitmapFactory.decodeResource(getResources(), R.drawable.servera6);
        serverA8 = BitmapFactory.decodeResource(getResources(), R.drawable.servera8);
        serverB4 = BitmapFactory.decodeResource(getResources(), R.drawable.serverb4);
        serverB6 = BitmapFactory.decodeResource(getResources(), R.drawable.serverb6);
        serverB8 = BitmapFactory.decodeResource(getResources(), R.drawable.serverb8);
        
        iv_serverA = (ImageView) myView.findViewById(R.id.iv_server_a);
        iv_serverB = (ImageView) myView.findViewById(R.id.iv_server_b);
        initServers();
        
        /* buttons to launch attacks */
        btnLaunch4 = (Button) myView.findViewById(R.id.btn_lnch4pwr);
        btnLaunch4.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		if(game.turn == 1 && game.p1moves >= 3 ||		// if player 1 and they have 3+ moves
        				game.turn == 2 && game.p2moves >= 3) {	// if player 2 and they have 3+
        			AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        			builder.setMessage("Which Server?")
        				.setCancelable(false)
        				.setPositiveButton("Server B", new DialogInterface.OnClickListener() {
        					public void onClick(DialogInterface dialog, int id) {
        						if(game.turn == 1 && !game.p1fullB || game.turn == 2 && !game.p2fullB) { 
	        						for(int i = 0; i < 4; i++) {
	        							if(game.turn == 1 && game.p1unitsB[i] == 0) {
	        								game.p1unitsB[i] = 4;
	        								game.p1moves -= 3;
	        								game.p1unitReport.add("Player 1 launches a level 4 attack on Server B!\n");
	        								if(i==3) game.p1fullB = true; // max number of units at Server
	        								tv_moves.setText("Moves: " + game.p1moves);
	        								break;
	        							}
	        							if(game.turn == 2 && game.p2unitsB[i] == 0) {
	        								game.p2unitsB[i] = 4;
	        								game.p2moves -= 3;
	        								game.p2unitReport.add("Player 2 launches a level 4 attack on Server B!\n");
	        								if(i==3) game.p2fullB = true;
	        								tv_moves.setText("Moves: " + game.p2moves);
	        								break;
	        							}
	        						}
        						}
        					}
        				})
        				.setNegativeButton("Server A", new DialogInterface.OnClickListener() {
        					public void onClick(DialogInterface dialog, int id) {
        						if(game.turn == 1 && !game.p1fullA || game.turn == 2 && !game.p2fullA) { 
	        						for(int i = 0; i < 4; i++) {
	        							if(game.turn == 1 && game.p1unitsA[i] == 0) {
	        								game.p1unitsA[i] = 4;
	        								game.p1moves -= 3;
	        								game.p1unitReport.add("Player 1 launches a level 4 attack on Server A!\n");
	        								if(i==3) game.p1fullA = true;
	        								tv_moves.setText("Moves: " + game.p1moves);
	        								break;
	        							}
	        							if(game.turn == 2 && game.p2unitsA[i] == 0) {
	        								game.p2unitsA[i] = 4;
	        								game.p2moves -= 3;
	        								game.p2unitReport.add("Player 2 launches a level 4 attack on Server A!\n");
	        								if(i==3) game.p2fullA = true;
	        								tv_moves.setText("Moves: " + game.p2moves);
	        								break;
	        							}
	        						}
        						}
        					}
        				});
        			AlertDialog alert = builder.create();
        			alert.show();
        		}
        	}
        });
        
        btnLaunch2 = (Button) myView.findViewById(R.id.btn_lnch2pwr);
        btnLaunch2.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		if(game.turn == 1 && game.p1moves >= 1 ||		// if player 1 and they have 1+ moves
        				game.turn == 2 && game.p2moves >= 1) {	// if player 2 and they have 1+ moves
        			AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        			builder.setMessage("Which Server?")
        				.setCancelable(false)
        				.setPositiveButton("Server B", new DialogInterface.OnClickListener() {
        					public void onClick(DialogInterface dialog, int id) {
        						if(game.turn == 1 && !game.p1fullB || game.turn == 2 && !game.p2fullB) { 
	        						for(int i = 0; i < 4; i++) {
	        							if(game.turn == 1 && game.p1unitsB[i] == 0) {
	        								game.p1unitsB[i] = 2;
	        								game.p1moves -= 1;
	        								game.p1unitReport.add("Player 1 launches a level 2 attack on Server B!\n");
	        								if(i==3) game.p1fullB = true;
	        								tv_moves.setText("Moves: " + game.p1moves);
	        								break;
	        							}
	        							if(game.turn == 2 && game.p2unitsB[i] == 0) {
	        								game.p2unitsB[i] = 2;
	        								game.p2moves -= 1;
	        								game.p2unitReport.add("Player 2 launches a level 2 attack on Server B!\n");
	        								if(i==3) game.p2fullB = true;
	        								tv_moves.setText("Moves: " + game.p2moves);
	        								break;
	        							}
	        						}
        						}
        					}
        				})
        				.setNegativeButton("Server A", new DialogInterface.OnClickListener() {
        					public void onClick(DialogInterface dialog, int id) {
        						if(game.turn == 1 && !game.p1fullA || game.turn == 2 && !game.p2fullA) {
	        						for(int i = 0; i < 4; i++) {
	        							if(game.turn == 1 && game.p1unitsA[i] == 0) {
	        								game.p1unitsA[i] = 2;
	        								game.p1moves -= 1;
	        								game.p1unitReport.add("Player 1 launches a level 2 attack on Server A!\n");
	        								if(i==3) game.p1fullA = true;
	        								tv_moves.setText("Moves: " + game.p1moves);
	        								break;
	        							}
	        							if(game.turn == 2 && game.p2unitsA[i] == 0) {
	        								game.p2unitsA[i] = 2;
	        								game.p2moves -= 1;
	        								game.p2unitReport.add("Player 2 launches a level 2 attack on Server A!\n");
	        								if(i==3) game.p2fullA = true;
	        								tv_moves.setText("Moves: " + game.p2moves);
	        								break;
	        							}
	        						}
        						}
        					}
        				});
        			AlertDialog alert = builder.create();
        			alert.show();
        		}
        	}
        });
        
        btnSteal = (Button) myView.findViewById(R.id.btn_steal);
        btnSteal.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		if(game.turn == 1 && game.p1moves >= 1 &&				// if player 1 and they have 1+ moves
        				!game.p1stealA && !game.p1stealB ||			// and they're not already stealing this turn
        				game.turn == 2 && game.p2moves >= 1 &&			// if player 2 and they have 1+ moves
        				!game.p2stealA && !game.p2stealB) {			// and they're not already stealing this turn
        			AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        			builder.setMessage("Steal a level 2 attack from which server?")
        				.setCancelable(false)
        				.setPositiveButton("Server B", new DialogInterface.OnClickListener() {
        					public void onClick(DialogInterface dialog, int id) {
        						if(game.turn == 1) {
        							game.p1stealB = true;
        							game.p1moves -= 1;
        							tv_moves.setText("Moves: " + game.p1moves);
        						}
        						if(game.turn == 2) {
        							game.p2stealB = true;
        							game.p2moves -= 1;
        							tv_moves.setText("Moves: " + game.p2moves);
        						}
        					}
        				})
        				.setNegativeButton("Server A", new DialogInterface.OnClickListener() {
        					public void onClick(DialogInterface dialog, int id) {
        						if(game.turn == 1) {
        							game.p1stealA = true;
        							game.p1moves -= 1;
        							tv_moves.setText("Moves: " + game.p1moves);
        						}
        						if(game.turn == 2) {
        							game.p2stealA = true;
        							game.p2moves -= 1;
        							tv_moves.setText("Moves: " + game.p2moves);
        						}
        					}
        				});
        			AlertDialog alert = builder.create();
        			alert.show();
        		}
        	}
        });
        
        /* button to end turn */
        btnEndTurn = (Button) myView.findViewById(R.id.btn_endturn);
        btnEndTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(game.turn == 1) swapToPassFrag();
            	if(game.turn == 2) swapToResultFrag();
            }
          });
        
        /* the views to which unit bmps are drawn */
        /*
        oppUnitsA = (UnitView) myView.findViewById(R.id.unitview1);
        oppUnitsB = (UnitView) myView.findViewById(R.id.unitview2);
        playerUnitsA = (UnitView) myView.findViewById(R.id.unitview3);
        playerUnitsB = (UnitView) myView.findViewById(R.id.unitview4);
        */
        
        oppUnit2 = BitmapFactory.decodeResource(getResources(), R.drawable.opp_unit_2);
        oppUnit4 = BitmapFactory.decodeResource(getResources(), R.drawable.opp_unit_4);
        playerUnit2 = BitmapFactory.decodeResource(getResources(), R.drawable.player_unit_2);
        playerUnit4 = BitmapFactory.decodeResource(getResources(), R.drawable.player_unit_4);
        
        iv_oppA1 = (ImageView) myView.findViewById(R.id.iv_opp_a1);
        iv_oppA2 = (ImageView) myView.findViewById(R.id.iv_opp_a2);
        iv_oppA3 = (ImageView) myView.findViewById(R.id.iv_opp_a3);
        iv_oppA4 = (ImageView) myView.findViewById(R.id.iv_opp_a4);
        iv_oppB1 = (ImageView) myView.findViewById(R.id.iv_opp_b1);
        iv_oppB2 = (ImageView) myView.findViewById(R.id.iv_opp_b2);
        iv_oppB3 = (ImageView) myView.findViewById(R.id.iv_opp_b3);
        iv_oppB4 = (ImageView) myView.findViewById(R.id.iv_opp_b4);
        iv_playerA1 = (ImageView) myView.findViewById(R.id.iv_player_a1);
        iv_playerA2 = (ImageView) myView.findViewById(R.id.iv_player_a2);
        iv_playerA3 = (ImageView) myView.findViewById(R.id.iv_player_a3);
        iv_playerA4 = (ImageView) myView.findViewById(R.id.iv_player_a4);
        iv_playerB1 = (ImageView) myView.findViewById(R.id.iv_player_b1);
        iv_playerB2 = (ImageView) myView.findViewById(R.id.iv_player_b2);
        iv_playerB3 = (ImageView) myView.findViewById(R.id.iv_player_b3);
        iv_playerB4 = (ImageView) myView.findViewById(R.id.iv_player_b4);
        
        initUnits();
        
        btnReset = (Button) myView.findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	reset();
            }
          });
        
        /* the text views of game info */
        tv_turn = (TextView) myView.findViewById(R.id.tv_playerturn);
        tv_moves = (TextView) myView.findViewById(R.id.tv_movesleft);
        tv_p1score = (TextView) myView.findViewById(R.id.tv_player1score);
        tv_p2score = (TextView) myView.findViewById(R.id.tv_player2score);
        
        /* increase player's remaining moves */
        gainMoves();
        
        /* display relative game info to TextViews */
        initText();
        
        /* display units remaining from last turn to their respective UnitView */
        //initUnits();
        
        return myView;
	}
	
	public void reset() {
		game.init();
		BoardFragment boardFragment = new BoardFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		transaction.replace(R.id.fragment_container, boardFragment);
		transaction.addToBackStack(null);
		
		transaction.commit();
	}
	
	public void swapToPassFrag() {
		
		PassFragment passFragment = new PassFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		transaction.replace(R.id.fragment_container, passFragment);
		transaction.addToBackStack(null);
		
		transaction.commit();
	}
	
	public void swapToResultFrag() {
		
		ResultFragment resultFragment = new ResultFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		transaction.replace(R.id.fragment_container, resultFragment);
		transaction.addToBackStack(null);
		
		transaction.commit();
	}
	
	public void gainMoves() {
		if(game.turn == 1)
			game.p1moves += 3;
		if(game.turn == 2)
			game.p2moves += 3;
	}
	
	public void initServers() {
		if(game.turn == 1) {
			if(game.p1serverA == 4) iv_serverA.setImageBitmap(serverA4);
			if(game.p1serverA == 6) iv_serverA.setImageBitmap(serverA6);
			if(game.p1serverA == 8) iv_serverA.setImageBitmap(serverA8);
			if(game.p1serverB == 4) iv_serverB.setImageBitmap(serverB4);
			if(game.p1serverB == 6) iv_serverB.setImageBitmap(serverB6);
			if(game.p1serverB == 8) iv_serverB.setImageBitmap(serverB8);
		}
		if(game.turn == 2) {
			if(game.p2serverA == 4) iv_serverA.setImageBitmap(serverA4);
			if(game.p2serverA == 6) iv_serverA.setImageBitmap(serverA6);
			if(game.p2serverA == 8) iv_serverA.setImageBitmap(serverA8);
			if(game.p2serverB == 4) iv_serverB.setImageBitmap(serverB4);
			if(game.p2serverB == 6) iv_serverB.setImageBitmap(serverB6);
			if(game.p2serverB == 8) iv_serverB.setImageBitmap(serverB8);
		}
	}
	
	public void initText() {
		
		if(game.turn == 1) {
			
			tv_turn.setText("P1 Turn");
			tv_moves.setText("Moves: " + game.p1moves);
			
		}
		else if(game.turn == 2) {
			
			tv_turn.setText("P2 Turn");
			tv_moves.setText("Moves: " + game.p2moves);
		}
		else Log.e("hackd", "TURN COUNTER OUT OF BOUNDS");
		tv_p1score.setText("P1 Score: " + game.p1score);
		tv_p2score.setText("P2 Score: " + game.p2score);
	}
	
	public void initUnits() {
		if(game.turn == 1) {
			if(game.p2remUnitsA[0] == 2) iv_oppA1.setImageBitmap(oppUnit2);
			if(game.p2remUnitsA[0] == 4) iv_oppA1.setImageBitmap(oppUnit4);
			if(game.p2remUnitsA[1] == 2) iv_oppA2.setImageBitmap(oppUnit2);
			if(game.p2remUnitsA[1] == 4) iv_oppA2.setImageBitmap(oppUnit4);
			if(game.p2remUnitsA[2] == 2) iv_oppA3.setImageBitmap(oppUnit2);
			if(game.p2remUnitsA[2] == 4) iv_oppA3.setImageBitmap(oppUnit4);
			if(game.p2remUnitsA[3] == 2) iv_oppA4.setImageBitmap(oppUnit2);
			if(game.p2remUnitsA[3] == 4) iv_oppA4.setImageBitmap(oppUnit4);
			
			if(game.p2remUnitsB[0] == 2) iv_oppB1.setImageBitmap(oppUnit2);
			if(game.p2remUnitsB[0] == 4) iv_oppB1.setImageBitmap(oppUnit4);
			if(game.p2remUnitsB[1] == 2) iv_oppB2.setImageBitmap(oppUnit2);
			if(game.p2remUnitsB[1] == 4) iv_oppB2.setImageBitmap(oppUnit4);
			if(game.p2remUnitsB[2] == 2) iv_oppB3.setImageBitmap(oppUnit2);
			if(game.p2remUnitsB[2] == 4) iv_oppB3.setImageBitmap(oppUnit4);
			if(game.p2remUnitsB[3] == 2) iv_oppB4.setImageBitmap(oppUnit2);
			if(game.p2remUnitsB[3] == 4) iv_oppB4.setImageBitmap(oppUnit4);
			
			if(game.p1unitsA[0] == 2) iv_playerA1.setImageBitmap(playerUnit2);
			if(game.p1unitsA[0] == 4) iv_playerA1.setImageBitmap(playerUnit4);
			if(game.p1unitsA[1] == 2) iv_playerA2.setImageBitmap(playerUnit2);
			if(game.p1unitsA[1] == 4) iv_playerA2.setImageBitmap(playerUnit4);
			if(game.p1unitsA[2] == 2) iv_playerA3.setImageBitmap(playerUnit2);
			if(game.p1unitsA[2] == 4) iv_playerA3.setImageBitmap(playerUnit4);
			if(game.p1unitsA[3] == 2) iv_playerA4.setImageBitmap(playerUnit2);
			if(game.p1unitsA[3] == 4) iv_playerA4.setImageBitmap(playerUnit4);
			
			if(game.p1unitsB[0] == 2) iv_playerB1.setImageBitmap(playerUnit2);
			if(game.p1unitsB[0] == 4) iv_playerB1.setImageBitmap(playerUnit4);
			if(game.p1unitsB[1] == 2) iv_playerB2.setImageBitmap(playerUnit2);
			if(game.p1unitsB[1] == 4) iv_playerB2.setImageBitmap(playerUnit4);
			if(game.p1unitsB[2] == 2) iv_playerB3.setImageBitmap(playerUnit2);
			if(game.p1unitsB[2] == 4) iv_playerB3.setImageBitmap(playerUnit4);
			if(game.p1unitsB[3] == 2) iv_playerB4.setImageBitmap(playerUnit2);
			if(game.p1unitsB[3] == 4) iv_playerB4.setImageBitmap(playerUnit4);
		}
		if(game.turn == 2) {
			if(game.p1remUnitsA[0] == 2) iv_oppA1.setImageBitmap(oppUnit2);
			if(game.p1remUnitsA[0] == 4) iv_oppA1.setImageBitmap(oppUnit4);
			if(game.p1remUnitsA[1] == 2) iv_oppA2.setImageBitmap(oppUnit2);
			if(game.p1remUnitsA[1] == 4) iv_oppA2.setImageBitmap(oppUnit4);
			if(game.p1remUnitsA[2] == 2) iv_oppA3.setImageBitmap(oppUnit2);
			if(game.p1remUnitsA[2] == 4) iv_oppA3.setImageBitmap(oppUnit4);
			if(game.p1remUnitsA[3] == 2) iv_oppA4.setImageBitmap(oppUnit2);
			if(game.p1remUnitsA[3] == 4) iv_oppA4.setImageBitmap(oppUnit4);
			
			if(game.p1remUnitsB[0] == 2) iv_oppB1.setImageBitmap(oppUnit2);
			if(game.p1remUnitsB[0] == 4) iv_oppB1.setImageBitmap(oppUnit4);
			if(game.p1remUnitsB[1] == 2) iv_oppB2.setImageBitmap(oppUnit2);
			if(game.p1remUnitsB[1] == 4) iv_oppB2.setImageBitmap(oppUnit4);
			if(game.p1remUnitsB[2] == 2) iv_oppB3.setImageBitmap(oppUnit2);
			if(game.p1remUnitsB[2] == 4) iv_oppB3.setImageBitmap(oppUnit4);
			if(game.p1remUnitsB[3] == 2) iv_oppB4.setImageBitmap(oppUnit2);
			if(game.p1remUnitsB[3] == 4) iv_oppB4.setImageBitmap(oppUnit4);
			
			if(game.p2unitsA[0] == 2) iv_playerA1.setImageBitmap(playerUnit2);
			if(game.p2unitsA[0] == 4) iv_playerA1.setImageBitmap(playerUnit4);
			if(game.p2unitsA[1] == 2) iv_playerA2.setImageBitmap(playerUnit2);
			if(game.p2unitsA[1] == 4) iv_playerA2.setImageBitmap(playerUnit4);
			if(game.p2unitsA[2] == 2) iv_playerA3.setImageBitmap(playerUnit2);
			if(game.p2unitsA[2] == 4) iv_playerA3.setImageBitmap(playerUnit4);
			if(game.p2unitsA[3] == 2) iv_playerA4.setImageBitmap(playerUnit2);
			if(game.p2unitsA[3] == 4) iv_playerA4.setImageBitmap(playerUnit4);
			
			if(game.p2unitsB[0] == 2) iv_playerB1.setImageBitmap(playerUnit2);
			if(game.p2unitsB[0] == 4) iv_playerB1.setImageBitmap(playerUnit4);
			if(game.p2unitsB[1] == 2) iv_playerB2.setImageBitmap(playerUnit2);
			if(game.p2unitsB[1] == 4) iv_playerB2.setImageBitmap(playerUnit4);
			if(game.p2unitsB[2] == 2) iv_playerB3.setImageBitmap(playerUnit2);
			if(game.p2unitsB[2] == 4) iv_playerB3.setImageBitmap(playerUnit4);
			if(game.p2unitsB[3] == 2) iv_playerB4.setImageBitmap(playerUnit2);
			if(game.p2unitsB[3] == 4) iv_playerB4.setImageBitmap(playerUnit4);
		}
	}
	
	/*
	public void initUnits() {
		
		if(game.turn == 1) {
			Log.i("hackd", "made it initUnits");
			
			oppUnitsA.setUnits(1, game.p2remUnitsA);
			Log.i("hackd", "called setUnits");
			oppUnitsB.setUnits(1, game.p2remUnitsB);
			playerUnitsA.setUnits(0, game.p1remUnitsA);
			playerUnitsB.setUnits(0, game.p1remUnitsB);
		}
		if(game.turn == 2) {
			oppUnitsA.setUnits(1, game.p1remUnitsA);
			oppUnitsB.setUnits(1, game.p1remUnitsB);
			playerUnitsA.setUnits(0, game.p2remUnitsA);
			playerUnitsB.setUnits(0, game.p2remUnitsB);
		}
		else Log.e("hackd", "TURN COUNTER OUT OF BOUNDS");
	}
	*/
}
