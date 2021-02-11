package com.teen.patti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PokerTable {

	Deck deck = null;
	Players pl = null;

	List<String> randCardsList = new ArrayList<>();
	List<String> playerList = new ArrayList<>();
	List<String> cardList = new ArrayList<>();
	Map<String, List<String>> finalList = new HashMap<>();
	List<Game> result = new ArrayList<Game>();

	public PokerTable() {
		super();
		deck = new Deck();
		pl = new Players();
	}

	public void addPlayers(String name) {
		pl.addPlayers(name);
	}

	private void shuffle() {
		Collections.shuffle(deck.getCollectionCards());
		randCardsList.addAll(deck.getCollectionCards());
	}

	private List<String> getRandCardsList() {
		return randCardsList;
	}

	public void setPlayerCard() {
		shuffle();
		Integer nop = pl.getPlayers().size();
		for (int i = 0; i < nop; i++) {
			int t = i;
			playerList.add(pl.getPlayers().get(i));
			System.out.println(pl.getPlayers().get(i));
			for (int j = 0; j < 3; j++) {
				cardList.add(getRandCardsList().get(t));
				System.out.println(getRandCardsList().get(t));
				t += nop;
			}
			System.out.println();
		}

		int c = 0;
		for (int i = 0; i < pl.getPlayers().size(); i++) {
			finalList.put(pl.getPlayers().get(i),
					Arrays.asList(cardList.get(c++), cardList.get(c++), cardList.get(c++)));
		}
		System.out.println(finalList + "\n");
	}

	private int cardValue(String cardVal) {
		int c = 0;
		for (Cards val : Cards.values()) {
			String card = val.getValue();
			if (card.equals(cardVal)) {
				c = val.getVal();
			}
		}
		return c;
	}

	public void cardIdentify() {
		int sum = 0;
		int seq[] = new int[3];
		String cards;
		Map<String, PlayingCards> tripletSequences = new HashMap<>();
		Map<String, PlayingCards> sequences = new HashMap<>();
		Map<String, PlayingCards> dupletSequences = new HashMap<>();
		Map<String, PlayingCards> higherOrders = new HashMap<>();

		for (int j = 0; j < finalList.size(); j++) {
			String player = pl.getPlayers().get(j);
			List<String> playerCards = finalList.get(pl.getPlayers().get(j));

			cards = finalList.get(player).toString();
			String val1 = playerCards.get(0);
			String val2 = playerCards.get(1);
			String val3 = playerCards.get(2);

			seq[0] = cardValue(val1);
			seq[1] = cardValue(val2);
			seq[2] = cardValue(val3);

			if (val1.equals(val2) && val2.equals(val3)) { // For Triplet
				if (seq[0] == 1)
					tripletSequences.put(player, new PlayingCards(cards, 14));
				else
					tripletSequences.put(player, new PlayingCards(cards, seq[0]));
				System.out.println("Reason : Triple");
			} else if (seq[0] + 1 == seq[1] && seq[0] + 2 == seq[2]) { // For Sequence
				sum = seq[0] + seq[1] + seq[2];
				sequences.put(player, new PlayingCards(cards, sum));
				System.out.println("Reason : Sequence");
			} else if (val1.equals(val2) || val2.equals(val3) || val3.equals(val1)) { // For Duplet
				sum = calcSum(seq);
				dupletSequences.put(player, new PlayingCards(cards, sum));
				System.out.println("Reason : Double");
			} else {
				sum = calcSum(seq);
				higherOrders.put(player, new PlayingCards(cards, sum));
				System.out.println("Reason : Higher Order");
			}
			System.out.println("Player : " + pl.getPlayers().get(j));
			System.out.println("Sum : " + sum);
			System.out.println("Cards : " + cards);
			System.out.println("=========================================");
		}
		Game winner = result(tripletSequences, sequences, dupletSequences, higherOrders);
		System.out.println("Winner Player");
		System.out.println("===========================================");
		System.out.println("Name : " + winner.getWinnerName());
		System.out.println("Card : " + winner.getCards());
	}

	private int calcSum(int[] seq) {
		int sum = 0;
		for (int i = 0; i < seq.length; i++)
			if (seq[i] == 1)
				seq[i] = 14;
		sum = seq[0] + seq[1] + seq[2];
		return sum;
	}

	private Game result(Map<String, PlayingCards> tripletSequences, Map<String, PlayingCards> sequences,
			Map<String, PlayingCards> dupletSequences, Map<String, PlayingCards> higherOrders) {
		if (tripletSequences.size() > 0) {
			return findWinner(tripletSequences);
		} else if (sequences.size() > 0) {
			return findWinner(sequences);
		} else if (dupletSequences.size() > 0) {
			return findWinner(dupletSequences);
		} else {
			return findWinner(higherOrders);
		}
	}

	private Game findWinner(Map<String, PlayingCards> givenSequences) {
		List<PlayingCards> values = new ArrayList<>();
		int k = 0;
		Iterator<String> itr;
		String key;
		Game winner = new Game();
		values.addAll(givenSequences.values());
		values.sort(Comparator.comparing(v -> v.getVal()));
		k = values.get(values.size() - 1).getVal();
		itr = givenSequences.keySet().iterator();
		while (itr.hasNext()) {
			key = (String) itr.next();
			if (givenSequences.get(key).getVal() == k) {
				winner.setWinnerName(key);
				winner.setCards(givenSequences.get(key).getCards());
				break;
			}
		}
		return winner;
	}

}
