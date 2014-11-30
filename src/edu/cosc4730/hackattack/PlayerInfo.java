package edu.cosc4730.hackattack;

public class PlayerInfo {
	
	private int playerAT, killCD, scanCD;			// number of player actions, kill move cooldown, scan move cooldown
	private int prob1Diff, prob2Diff;				// number of player actions, kill move cooldown, scan move cooldown
	private int pwrAtProb1, pwrAtProb2;				// summation of power at problem 1, summation of power at problem 2
	private int num2PwrAtProb1, num2PwrAtProb2;		// number of 2 power units at problem 1, number of 2 power units at problem 2
	
	public PlayerInfo() {
		playerAT = 0; killCD = 0; scanCD = 0;		
		prob1Diff = 0; prob2Diff = 0;					
		pwrAtProb1 = 0; pwrAtProb2 = 0;				
		num2PwrAtProb1 = 0; num2PwrAtProb2 = 0;		
	}
	
	public void setAT(int at) { playerAT = at; }
	public void setKillCD(int cd) { killCD = cd; }
	public void setScanCD(int cd) { scanCD = cd; }
	public void setProb1Diff( int diff ) { prob1Diff = diff; }
	public void setProb2Diff( int diff ) { prob2Diff = diff; }
	public void setPwrAtProb1( int pwr ) { pwrAtProb1 = pwr; }
	public void setPwrAtProb2( int pwr ) { pwrAtProb2 = pwr; }
	public void num2PwrAtProb1( int num ) { num2PwrAtProb1 = num; }
	public void num2PwrAtProb2( int num ) { num2PwrAtProb2 = num; }
	
	public int getAT() { return playerAT; }
	public int getKillCD() { return killCD; }
	public int getScanCD() { return scanCD; }
	public int getProb1Diff() { return prob1Diff; }
	public int getProb2Diff() { return prob2Diff; }
	public int getPwrAtProb1() { return pwrAtProb1; }
	public int getPwrAtProb2() { return pwrAtProb2; }
	public int getNum2PwrAtProb1() {return num2PwrAtProb1; }
	public int getNum2PwrAtProb2() {return num2PwrAtProb2; }
}
