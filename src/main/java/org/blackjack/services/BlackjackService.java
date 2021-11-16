package org.blackjack.services;

import java.util.Scanner;

import org.blackjack.exceptions.StopRoundException;
import org.blackjack.models.Deck;

public class BlackjackService {

	private static BlackjackService instance;

	private BlackjackService() {

	}

	public static BlackjackService newInstance() {

		if (instance == null) {
			instance = new BlackjackService();
		}
		return instance;
	}

	public void distributeCards(Deck player, Deck dealer, Deck deck) {

		// Player gets two cards
		player.draw(deck);
		player.draw(deck);

		// Dealer gets two cards
		dealer.draw(deck);
		dealer.draw(deck);
	}

	public void hitOrStand(Scanner userInput, Deck player, Deck deck, double playerMoney, double playerBet,
			boolean endRound) throws StopRoundException {
		System.out.println("Would you like to (1) Hit or (2) Stand?");
		int response = userInput.nextInt();

		if (response == 1) {
			player.draw(deck);
			System.out.println("You draw a: " + player.getCard(player.deckSize() - 1).toString());
			if (player.cardsValue() > 21) {
				System.out.println("Bust. Currently valued at: " + player.cardsValue());
				playerMoney -= playerBet;
				endRound = true;
				throw new StopRoundException();
			}
		}

		if (response == 2) {
			throw new StopRoundException();
		}
	}

	public void showResults(Deck player, Deck dealer) {
		System.out.println("Your hand:");
		System.out.println(player.toString());
		System.out.println("Your deck is valued at: " + player.cardsValue());

		System.out.println("Dealer hand: " + dealer.getCard(0).toString() + " and [hidden]");

	}

	public void playerLose(double playerMoney, double playerBet, boolean endRound) {
		playerMoney -= playerBet;
		endRound = true;
	}

	public void playerWins(double playerMoney, double playerBet, boolean endRound) {
		playerMoney += playerBet;
		endRound = true;

	}

	public void createThreeDecks(Deck deck) {
		deck.createFullDeck();
		deck.createFullDeck();
		deck.createFullDeck();

	}

}
