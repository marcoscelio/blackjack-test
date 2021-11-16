package org.blackjack.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.blackjack.models.Value.*;

public class Deck {
	private List<Card> cards;

	public Deck() {
		this.cards = new ArrayList<Card>();
	}

	public void createFullDeck() {

		for (Suit suit : Suit.values()) {
			for (Value value : Value.values()) {
				this.cards.add(new Card(suit, value));
			}
		}

	}

	public void shuffle() {
		List<Card> tmpDeck = new ArrayList<Card>();
		Random random = new Random();
		int randomCardIndex = 0;
		int originalDeckSize = this.cards.size();
		for (int i = 0; i < originalDeckSize; i++) {
			randomCardIndex = random.nextInt(this.cards.size());
			tmpDeck.add(this.cards.get(randomCardIndex));
			this.cards.remove(randomCardIndex);
		}
		this.cards = tmpDeck;
	}

	public Card removeCard(int index) {
		return this.cards.remove(index);
	}

	public Card getCard(int index) {
		return this.cards.get(index);
	}

	public void addCard(Card card) {
		this.cards.add(card);
	}

	public void draw(Deck deck) {
		this.cards.add(deck.getCard(0));
		deck.removeCard(0);
	}

	public int cardsValue() {
		int total = 0;
		int aces = 0;
		for (Card card : this.cards) {
			switch (card.getValue()) {
			case TWO:
				total += 2;
				break;
			case THREE:
				total += 3;
				break;
			case FOUR:
				total += 4;
				break;
			case FIVE:
				total += 5;
				break;
			case SIX:
				total += 6;
				break;
			case SEVEN:
				total += 7;
				break;
			case EIGHT:
				total += 8;
				break;
			case NINE:
				total += 9;
				break;
			case TEN:
				total += 10;
				break;
			case JACK:
				total += 10;
				break;
			case QUEEN:
				total += 10;
				break;
			case KING:
				total += 10;
				break;
			case ACE:
				total += 1;
				break;
			}
		}
		for (int i = 0; i < aces; i++) {
			if (total > 10) {
				total += 1;
			} else {
				total += 11;
			}
		}
		return total;
	}

	public int deckSize() {
		return this.cards.size();
	}

	public void moveAllToDeck(Deck moveTo) {
		int deckSize = this.cards.size();
		for (int i = 0; i < deckSize; i++) {
			moveTo.addCard(this.getCard(i));
		}
		for (int i = 0; i < deckSize; i++) {
			this.removeCard(0);
		}

	}

	@Override
	public String toString() {
		StringBuffer cardListOutput = new StringBuffer();
		for (Card card : this.cards) {
			cardListOutput.append("\n" + card.toString());
		}
		return cardListOutput.toString();
	}

}
