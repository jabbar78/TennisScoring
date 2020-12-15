package dius.tennis;

public class Player {

	// Constructor

	Player(String name, String desc) {
		playerName = name;
		playerDescription = desc;

		System.out.println("Player " + getPlayerNameDesc() + " has joined the match!");
	}

	// Names, descriptions

	private String playerName;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	private String playerDescription;

	public String getPlayerDescription() {
		return playerDescription;
	}

	public void setPlayerDescription(String playerDescription) {
		this.playerDescription = playerDescription;
	}

	public String getPlayerNameDesc() {
		return this.playerName + " (" + this.playerDescription + ")";
	}

	// Score registers

	/*
	 * For points, the numbers will be "normalised" (15 -> 1, 30 -> 2, 40 -> 3) this
	 * enables us to easily translate the game rules (described in
	 * "normalised form", in the ScoreBoard class)
	 */
	private int points = 0;

	public void setPoints(int points) {
		this.points = points;
	}

	public void incrementPoints() {
		this.points++;
	}

	public void decrementPoints() {
		this.points--;
	}

	public int getPoints() {
		return this.points;
	}
	


	private int games = 0;

	public void setGames(int games) {
		this.games = games;
	}

	public void incrementGames() {
		this.games++;
	}

	public void decrementGames() {
		this.games--;
	}

	public int getGames() {
		return games;
	}

}
