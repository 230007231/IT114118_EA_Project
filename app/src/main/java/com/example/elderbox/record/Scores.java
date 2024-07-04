package com.example.elderbox.record;

import java.io.Serializable;

public class Scores implements Serializable {
	// Declare an integer variable to store an ID.
	public int id;
	// Declare a String variable to store the player's name.
	public String playername;

	// Declare integer variables to store different scores.
	public int score1;
	public int score2;
	public int score3;
	public int score4;

	// Override the toString() method to create a formatted string representation.
	public String toString() {
		return playername + " Score 1: " + score1
				+ " Score 2: " + score2
				+ " Score 3: " + score3
				+ " Score 4: " + score4;

	}
}
