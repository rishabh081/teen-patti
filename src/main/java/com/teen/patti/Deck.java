package com.teen.patti;

import java.util.ArrayList;
import java.util.List;

public class Deck {

	List<String> collectionCards = new ArrayList<>();

	public Deck() {
		Cards cards[] = Cards.values();

		for (int j = 0; j < cards.length; j++) {
			collectionCards.add(cards[j].getValue());
		}
	}

	public List<String> getCollectionCards() {
		return collectionCards;
	}

	public void setCollectionCards(List<String> collectionCards) {
		this.collectionCards = collectionCards;
	}
}
