package com.teen.patti;

public class Game {

	private String winnerName;
	private String cards;

	public Game() {

	}

	public Game(String winnerName, String cards) {
		this.winnerName = winnerName;
		this.cards = cards;
	}

	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}

	public void setCards(String cards) {
		this.cards = cards;
	}

	public String getWinnerName() {
		return winnerName;
	}

	public String getCards() {
		return cards;
	}
}
