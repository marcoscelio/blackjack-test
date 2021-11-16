package org.blackjack.models;

public class Card {
	private Suit suit;
	private Value value;

	public Card(Suit suit, Value value) {
		this.suit = suit;
		this.value = value;
	}

	public Value getValue() {
		return this.value;

	}

	@Override
	public String toString() {
		return this.value.toString() + "-" + this.suit.toString();
	}

}
