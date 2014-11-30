package edu.cosc4730.hackattack;

public class GameInfo {
	
	private int playerTurn;		// represents current player's turn
	
	public GameInfo() {
		playerTurn = 0;
	}
	
	public void setTurn(int turn) {
		playerTurn = turn;
	}
	
	public int getTurn() {
		return playerTurn;
	}
}
