package edu.cosc4730.hackattack;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ResultFragment extends Fragment {
	
	MainActivity game;
	boolean gameOver, resetA, resetB;
	int p1ABoost, p1BBoost, p2ABoost, p2BBoost;
	String output;

	public ResultFragment() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		
		game = (MainActivity) getActivity();
        View myView = inflater.inflate(R.layout.fragment_result, container, false);
        
        gameOver = false; resetA = false; resetB = false;
        p1ABoost = 0; p1BBoost = 0; p2ABoost = 0; p2BBoost = 0;
        output = "";
        
        TextView resultOut = (TextView) myView.findViewById(R.id.output);
        resultOut.setText(""); // initialize output
        
        /* append reports of attacks launched by player 1 and 2 to output */
        addUnitReport();
        
        /* display attempted steals by player 1 and 2 */
        checkSteals();
        
        /* check for and display points scored by player 1 and 2 */
        checkScore();
        
        /* refresh hacked servers */
        checkServers();
        
        /* check for win condition */
        checkWinCon();
        
        /* display full report to resultOut */
        resultOut.setText(output);
        
        /* moving back to player 1's turn; decrement turn integer */
        game.turn--;
        
        /* if gameOver restart game else pass back to player 1 */
        Button btnPass = (Button) myView.findViewById(R.id.btn_continue2);
        if(gameOver) btnPass.setText("New Game");
        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(gameOver) game.init();
            	swapToBoardFrag();
            }
          });
        
        return myView;
	}
	
	public void swapToBoardFrag() {
		
		BoardFragment boardFragment = new BoardFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		transaction.replace(R.id.fragment_container, boardFragment);
		transaction.addToBackStack(null);
		
		transaction.commit();
	}
	
	public void addUnitReport() {
		for(String report : game.p1unitReport) {
			output += report;
		}
		output += "\n";
		for(String report : game.p2unitReport) {
			output += report;;
		}
		output += "\n";
		
		/* report has been added; now clear */
		game.p1unitReport.clear();
		game.p2unitReport.clear();
	}
	
	public void checkSteals() {
		if(game.p1stealA && game.p2stealA || game.p1stealB && game.p2stealB) {
			output += "Both players attempted to repurpose attacks at Server A!\n";
		}
		else if(game.p1stealA) {
			for(int i = 0; i < 4; i++) {
				if(game.p2unitsA[i] == 2) {
					game.p2unitsA[i] = 0;
					game.p2fullA = false;
					output += "Player 1 repurposed a level 2 attack at Server A!\n";
					if(game.p1fullA) p1ABoost = 2;
					else {
						for(int j = 0; j < 4; j++) {
							if(game.p1unitsA[j] == 0){
								game.p1unitsA[j] = 2;
								game.p1remUnitsA[j] = 2;
								if(j==3) game.p1fullA = true;
								break;
							}
						}
					}
					break;
				}
			}
		}
		else if(game.p1stealB) {
			for(int i = 0; i < 4; i++) {
				if(game.p2unitsB[i] == 2) {
					game.p2unitsB[i] = 0;
					game.p2fullB = false;
					output += "Player 1 repurposed a level 2 attack at Server B!\n";
					if(game.p1fullB) p1BBoost = 2;
					else {
						for(int j = 0; j < 4; j++) {
							if(game.p1unitsB[j] == 0) {
								game.p1unitsB[j] = 2;
								game.p1remUnitsB[j] = 2;
								if(j==3) game.p1fullB = true;
								break;
							}
						}
					}
					break;
				}
			}
		}
		if(game.p2stealA && !game.p1stealA) {
			for(int i = 0; i < 4; i++) {
				if(game.p1unitsA[i] == 2) {
					game.p1unitsA[i] = 0;
					game.p1fullA = false;
					output += "Player 2 repurposed a level 2 attack at Server A!\n";
					if(game.p2fullA) p2ABoost = 2;
					else {
						for(int j = 0; j < 4; j++) {
							if(game.p2unitsA[j] == 0) {
								game.p2unitsA[j] = 2;
								game.p2remUnitsA[j] = 2;
								if(j==3) game.p2fullA = true;
								break;
							}
						}
					}
					break;
				}
			}
		}
		else if(game.p2stealB && !game.p1stealB) {
			for(int i = 0; i < 4; i++) {
				if(game.p1unitsB[i] == 2) {
					game.p1unitsB[i] = 0;
					game.p1fullB = false;
					output += "Player 2 repurposed a level 2 attack at Server B!\n";
					if(game.p2fullB) p2BBoost = 2;
					else {
						for(int j = 0; j < 4; j++) {
							if(game.p2unitsB[j] == 0) {
								game.p2unitsB[j] = 2;
								game.p2remUnitsB[j] = 2;
								if(j==3) game.p2fullB = true;
								break;
							}
						}
					}
					break;
				}
			}
		}
		output += "\n";
		
		/* reset steals for next turn */
		game.p1stealA = false; game.p1stealB = false;
		game.p2stealA = false; game.p2stealB = false;
	}
	
	public void checkScore() {
		
		/* compute potential points for each server */
		int p1pointsA = 0; int p1pointsB = 0;
		int p2pointsA = 0; int p2pointsB = 0;
		int points = 3; // default
		
		for(int i = 4; i < 9; i+=2) {						// point breakdown:
			if(game.p1serverA == i) p1pointsA = points;	// encryption level 4 -> 3 points
			if(game.p1serverB == i) p1pointsB = points;	// encryption level 6 -> 4 points
			if(game.p2serverA == i) p2pointsA = points;	// encryption level 8 -> 5 points
			if(game.p2serverB == i) p2pointsB = points;
			points++;										// about to check next tier of encryption level; increment points to be awarded
		}
		
		/* compute total summation of attacks at servers */
		int p1powerA = 0; int p1powerB = 0;
		int p2powerA = 0; int p2powerB = 0;	
			
		for(int i = 0; i < 4; i++) {
			p1powerA += game.p1unitsA[i] + p1ABoost;	// summation of P1's attacks at Server A
			p1powerB += game.p1unitsB[i] + p1BBoost;	// summation of P1's attacks at Server B
			p2powerA += game.p2unitsA[i] + p2ABoost;	// summation of P2's attacks at Server A
			p2powerB += game.p2unitsB[i] + p2BBoost;	// summation of P1's attacks at Server A
		}
		
		/* check power summations against encryption levels */
		if(p1powerA >= game.p1serverA) {	// does the player's hack beat the server's encryption level?
			game.p1score += p1pointsA;		// if yes, grant points based on encryption level difficulty
			resetA = true;					// and set flag to reinitialize server
			output += "Player 1 has hacked Server A for " + p1pointsA + " points!\n";
		}
		if(p1powerB >= game.p1serverB) {
			game.p1score += p1pointsB;
			resetB = true;
			output += "Player 1 has hacked Server B for " + p1pointsB + " points!\n";
		}
		if(p2powerA >= game.p2serverA) {
			game.p2score += p2pointsA;
			resetA = true;
			output += "Player 2 has hacked Server A for " + p2pointsA + " points!\n";
		}
		if(p2powerB >= game.p2serverB) { 
			game.p2score += p2pointsB;
			resetB = true;
			output += "Player 2 has hacked Server B for " + p2pointsB + " points!\n";
		}
		output += "\n";
	}
	
	public void checkServers() {
		if(resetA) {
			Log.i("hackd", "resetting Server A");
			output += "Server A has been reset after being hacked.\n";
			game.initServer(1, 0);
			game.initServer(2, 0);
			clearUnits(0);
		}
		else addRemUnits(0);
		if(resetB) {
			Log.i("hackd", "resetting Server B");
			output += "Server B has been reset after being hacked.\n\n";
			game.initServer(1, 1);
			game.initServer(2, 1);
			clearUnits(1);
		}
		else addRemUnits(1);
	}
	
	public void clearUnits(int server) {	// where 0 -> Server A and 1 -> Server B
		for(int i = 0; i < 4; i++) {
			if(server == 0) {				// if Server A was hacked
				game.p1unitsA[i] = 0;		// clear all Player 1's
				game.p2unitsA[i] = 0;       // and all of Player 2's units
				game.p1remUnitsA[i] = 0;	// from Server A
				game.p2remUnitsA[i] = 0;
				game.p1fullA = false;
				game.p2fullA = false;
			}								
			if(server == 1) {				// if Server B was hacked
				game.p1unitsB[i] = 0;		// clear all of Player 1's
				game.p2unitsB[i] = 0;		// and all of Player 2's units
				game.p1remUnitsB[i] = 0;	// from Server B
				game.p2remUnitsB[i] = 0;
				game.p1fullB = false;
				game.p2fullB = false;
			}								
		}
	}
	
	public void addRemUnits(int server) {
		for(int i = 0; i < 4; i++) {
			if(server == 0) {
				game.p1remUnitsA[i] = game.p1unitsA[i];
				game.p2remUnitsA[i] = game.p2unitsA[i];
			}
			if(server == 1) {
				game.p1remUnitsB[i] = game.p1unitsB[i];
				game.p2remUnitsB[i] = game.p2unitsB[i];
			}
		}
	}
	
	public void checkWinCon() {
		if(game.p1score >= 15 && game.p2score >= 15) {
			gameOver = true;
			output += "Both players have reached 15 points and won!";
		}
		else if(game.p1score >= 15) {
			gameOver = true;
			output += "Player 1 has reached 15 points and won!";
		}
		else if(game.p2score >= 15) {
			gameOver = true;
			output += "Player 2 has reached 15 points and won!";
		}
	}
}
