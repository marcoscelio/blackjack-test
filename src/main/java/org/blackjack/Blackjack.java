package org.blackjack;

import java.util.Scanner;
import org.blackjack.exceptions.StopRoundException;
import org.blackjack.models.Deck;
import org.blackjack.services.BlackjackService;

public class Blackjack {

	public static void main(String[] args) {
		// Welcome message
		System.out.println("Welcome to blackjack");

		BlackjackService service = BlackjackService.newInstance();

		Deck deck = new Deck();

		// Requirement 3
		service.createThreeDecks(deck);
		deck.shuffle();

		Deck player = new Deck();
		Deck dealer = new Deck();

		double playerMoney = 100.00;
		try {
			Object initialMoneyValue = System.getenv().get("INITIAL_MONEY_AVAILABLE");
			if (initialMoneyValue == null) {
				System.out.println(
						"Initial money available value environment variable 'INITIAL_MONEY_AVAILABLE' is not set. Using default value");
			} else {
				playerMoney = Double.parseDouble(System.getenv().get("INITIAL_MONEY_AVAILABLE").toString());
			}
		} catch (Exception error) {
			System.out.println(
					"Error reading initial money available 'INITIAL_MONEY_AVAILABLE' environment variable. Using default value");
		}

		Scanner userInput = new Scanner(System.in);

		// Loop for input
		while (playerMoney > 0) {
			System.out.println("You have " + playerMoney + ". How much do You like to bet?");
			double playerBet = userInput.nextDouble();
			if (playerBet > playerMoney) {
				System.out.println("You have not enough money. Please bet a lower value.");
				continue;
			}
			boolean endRound = false;

			// Distribute cards
			service.distributeCards(player, dealer, deck);

			while (true) {
				service.showResults(player, dealer);

				try {
					service.hitOrStand(userInput, player, deck, playerMoney, playerBet, endRound);
				} catch (StopRoundException e) {
					break;
				}

			}

			// Reveal Dealer cards
			System.out.println("Dealer cards: " + dealer.toString());
			if ((dealer.cardsValue() > player.cardsValue()) && !endRound) {
				System.out.println("Dealer beats you!");
				playerMoney -= playerBet;
				endRound = true;
			}

			while (dealer.cardsValue() < 17 && !endRound) {
				dealer.draw(deck);
				System.out.println("Dealer Draws: " + dealer.getCard(dealer.deckSize() - 1).toString());
			}

			// Display total for dealer
			System.out.println("Dealer's hand is valued at: " + dealer.cardsValue());
			if (dealer.cardsValue() > 21 && !endRound) {
				System.out.println("Dealer busts. You win.");
				playerMoney += playerBet;
				endRound = true;
			}

			// Determine if push
			if (dealer.cardsValue() == player.cardsValue() && !endRound) {
				System.out.println("Push");
				endRound = true;
			}

			if (player.cardsValue() > dealer.cardsValue() && !endRound) {
				System.out.println("You win the hand!");
				playerMoney += playerBet;
				endRound = true;
			} else if (!endRound) {
				System.out.println("You lose the hand!");
				playerMoney -= playerBet;
				endRound = true;
			}

			player.moveAllToDeck(deck);
			dealer.moveAllToDeck(deck);

		}
		System.out.println("Game over.You are out of money.");

	}

}
