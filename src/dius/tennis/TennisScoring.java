package dius.tennis;

import java.util.Random;

public class TennisScoring {

	public static void main(String[] args) {
		// Instantiate ScoreBoard, which creates Players
		ScoreBoard sb = new ScoreBoard("Roger Federer", "Right handed, single handed backhand, GOAT", 
				"Rafael Nadal", "Left handed, double handed backhand, also a GOAT");
		
		// Randomise the points between players until game is won
		Random random = new Random();
		int nextPlayerWinID;
		
		while (!sb.isMatchWon()) {
			nextPlayerWinID = random.nextInt(2) + 1;
			if (nextPlayerWinID == 1) {
				sb.player1_winPoint();
			}
			else {
				sb.player2_winpoint();
			}
		}
		
		// Match is won. Print out winner's name
		System.out.println("\n----------------------\n*******************\nGame, Set, Match: " 
		+ sb.getPlayerGameSetMatch().getPlayerNameDesc());
	}

}
