package dius.tennis;

public class ScoreBoard {

	private Player player1;
	private Player player2;

	ScoreBoard(String player1_name, String player1_desc, String player2_name, String player2_desc) {
		player1 = new Player(player1_name, player1_desc);
		player2 = new Player(player2_name, player2_desc);
	}

	public boolean player1_winPoint() {
		System.out.println("------");
		System.out.println(player1.getPlayerName() + " has won the point..");
		calculatePoint(player1, player2);
		return isMatchWon();
	}

	public boolean player2_winpoint() {
		System.out.println("------");
		System.out.println(player2.getPlayerName() + " has won the point..");
		calculatePoint(player2, player1);
		return isMatchWon();
	}

	public static int NUMBER_OF_SETS = 1;

	// Coding system for points
	public static int LOVE = 0;
	public static int FIFTEEN = 1;
	public static int THIRTY = 2;
	public static int FORTY = 3;
	public static int ADVANTAGE = 4;

	// Tie break flag
	private boolean tieBreak = false;

	private boolean isTieBreak() {
		return tieBreak;
	}

	// Match outcome flag
	private boolean matchWon = false;

	public boolean isMatchWon() {
		return matchWon;
	}

	Player playerGameSetMatch = null;

	public Player getPlayerGameSetMatch() {
		return playerGameSetMatch;
	}

	private void setPlayerGameSetMatch(Player player) {
		playerGameSetMatch = player;
		matchWon = true;
	}

	/**
	 * 
	 * @param playerWhoWonPoint
	 * @param playerWhoLostPoint
	 * @return MatchOutcome
	 */
	private void calculatePoint(Player playerWhoWonPoint, Player playerWhoLostPoint) {

		// Tie break scenario
		if (isTieBreak()) {

			// Calculate points difference
			int pointsDiff = playerWhoWonPoint.getPoints() - playerWhoLostPoint.getPoints();

			if (playerWhoWonPoint.getPoints() < 6) {
				playerWhoWonPoint.incrementPoints();
			} else if (playerWhoWonPoint.getPoints() == 6 && playerWhoLostPoint.getPoints() < 6) {
				playerWhoWonPoint.incrementPoints();
				wonGame(playerWhoWonPoint, playerWhoLostPoint); // ** WON GAME
			} else if (playerWhoWonPoint.getPoints() > 6 && pointsDiff >= 1) {
				playerWhoWonPoint.incrementPoints();
				wonGame(playerWhoWonPoint, playerWhoLostPoint); // ** WON GAME
			}
		}
		// Normal game scenario
		else {
			// If score is 0 (0), 15 (1) or 30 (2)
			if (playerWhoWonPoint.getPoints() <= THIRTY) {
				playerWhoWonPoint.incrementPoints();
			}
			// if score is 40 (3), there are two options depending on opponent's score
			else if (playerWhoWonPoint.getPoints() == FORTY) {
				if (playerWhoLostPoint.getPoints() == FORTY) {
					// Player goes to ADVANTAGE
					playerWhoWonPoint.incrementPoints();
				} else if (playerWhoLostPoint.getPoints() == ADVANTAGE) {
					// If opponent is at AD, then bring back to DEUCE
					playerWhoLostPoint.decrementPoints();
				} else if (playerWhoLostPoint.getPoints() <= THIRTY) {
					// If opponent is at THIRTY or less, the player wins point - and game
					wonGame(playerWhoWonPoint, playerWhoLostPoint); // ** WON GAME
				}
			}
			// if score is AD., player wins game
			else if (playerWhoWonPoint.getPoints() == ADVANTAGE) {
				wonGame(playerWhoWonPoint, playerWhoLostPoint); // ** WON GAME
			}
		}

		// Print score for normal game
		if (!isTieBreak()) {
			System.out.println(
					player1.getPlayerName() + "\t - " + printPoints(player1.getPoints()) + " - " + player1.getGames());
			System.out.println(
					player2.getPlayerName() + "\t - " + printPoints(player2.getPoints()) + " - " + player2.getGames());
		}

		// Print score for a tie break game
		else {
			System.out.println("\n=========\nTie-break\n========");
			System.out.println(player1.getPlayerName() + "\t - (" + printPoints(player1.getPoints()) + ") - "
					+ player1.getGames());
			System.out.println(player2.getPlayerName() + "\t - (" + printPoints(player2.getPoints()) + ") - "
					+ player2.getGames());
		}
	}

	/**
	 * This method returns "tennis" points (15, 30, 40, ADV), converting from
	 * integer points (0,1,2,3,4)
	 * 
	 * @return
	 */
	public String printPoints(int points) {
		if (!this.isTieBreak()) {
			switch (points) {
			case 0:
				return "00";
			case 1:
				return "15";
			case 2:
				return "30";
			case 3:
				return "40";
			case 4:
				return "AD";
			}
		} else { // if a tie breaker
			return Integer.toString(points);
		}

		System.err.println("Error: Points has gone beyond limit of 4: " + points);
		return "Error";
	}

	/**
	 * This method is called when player has won a game. it also checks if they have
	 * won the match and sets appropriate flag.
	 * 
	 * @param playerWhoWonPoint
	 * @param playerWhoLostPoint
	 */
	private void wonGame(Player playerWhoWonPoint, Player playerWhoLostPoint) {

		// Print game result
		System.out.println("** " + playerWhoWonPoint.getPlayerName() + " wins game...");

		/*
		 * First reset the points
		 */
		if (!this.isTieBreak()) {
			this.player1.setPoints(0);
			this.player2.setPoints(0);
		}

		/*
		 * Increment game counter
		 */
		playerWhoWonPoint.incrementGames();

		/*
		 * If it is a tie breaker and you won the game, you've won the match (1 set
		 * match).
		 */
		if (this.isTieBreak()) {
			setPlayerGameSetMatch(playerWhoWonPoint); // ** GAME SET MATCH
		}

		/*
		 * Player who won game < 5, so keep playing
		 */
		else if (playerWhoWonPoint.getGames() < 5) {
			// Do nothing - Play another game
		}
		/*
		 * Player who won game still at 5, and opponent < 5 -- Play another game
		 */
		else if (playerWhoWonPoint.getGames() <= 5 && playerWhoLostPoint.getGames() <= 5) {
			// Do nothing - Play another game
		}
		/*
		 * If both players on 6 games - TIE BREAK!
		 */
		else if (playerWhoWonPoint.getGames() == 6 && playerWhoLostPoint.getGames() == 6) {
			tieBreak = true;
		}
		/*
		 * 7 - 5 -> WON MATCH
		 */
		else if (playerWhoWonPoint.getGames() == 7 && playerWhoLostPoint.getGames() == 5) {
			setPlayerGameSetMatch(playerWhoWonPoint); // ** GAME SET MATCH
		}
		/*
		 * If player who won game is 6 or more, and opponent is 5 or less, - WON MATCH
		 */
		else if (playerWhoWonPoint.getGames() >= 6 && playerWhoLostPoint.getGames() < 5) {
			setPlayerGameSetMatch(playerWhoWonPoint); // ** GAME SET MATCH
		}

		else {
			// Keep playing ...
		}

	}

}
