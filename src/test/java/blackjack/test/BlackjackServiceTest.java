package blackjack.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.blackjack.models.Deck;
import org.blackjack.services.BlackjackService;
import org.junit.jupiter.api.Test;

class BlackjackServiceTest {

	private BlackjackService testInstance = BlackjackService.newInstance();

	@Test
	void givenDeckIsInstantiatedThenCardsValueIsZeroTest() {		
		Deck single = new Deck();
		single.createFullDeck();
		int singleDeckCardsValue = single.cardsValue();
		
		Deck deck = new Deck();
		testInstance.createThreeDecks(deck);

		int total = deck.cardsValue();
		assertEquals(total, singleDeckCardsValue * 3);
	}

}