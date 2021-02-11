package com.teen.patti;

public class PlayingCards {

	private String cards;
	private int val;

	public PlayingCards() {
	}

	public PlayingCards(String cards, int val) {
		this.cards = cards;
		this.val = val;
	}

	public String getCards() {
		return cards;
	}

	public void setCards(String cards) {
		this.cards = cards;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}
}
