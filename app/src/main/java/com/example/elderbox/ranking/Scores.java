package com.example.elderbox.ranking;

import java.io.Serializable;

/*
        {
                "id": "c200",
                "name": "Ravi Tamada",
                "email": "ravi@gmail.com",
                "address": "xx-xx-xxxx,x - street, x - country",
                "gender" : "male",
                "phone": {
                    "mobile": "+91 0000000000",
                    "home": "00 000000",
                    "office": "00 000000"
                }
        }
 */
public class Scores implements Serializable {
	public int id;
	public String playername;
	public String playerpw;
	public int score1;
	public int score2;
	public int score3;
	public int score4;


	public String toString() {
		return playername + "Score 1: " + score1
				+ "Score 2: " + score2
				+ "Score 3: " + score3
				+ "Score 4: " + score4;

	}
}
