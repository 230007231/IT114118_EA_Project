package com.example.elderbox.ranking;

import java.io.Serializable;

public class Users implements Serializable {

	// Fields for user data
	public String name;
	public int score1;
	public int score2;
	public int score3;
	public int score4;

	// Custom toString method to display user information
	public String toString() {
		return "Name: " + name
				+ " Score 1: " + score1
				+ " Score 2: " + score2
				+ " Score 3: " + score3
				+ " Score 4: " + score4;
	}
}