package tests;

import blackjack.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void getDeck() {
        Deck testDeck = new Deck();
        assertEquals(testDeck.cardsLeft(), 52);
        Card topCard = testDeck.drawCard();
        assertNotNull(topCard);
//        System.out.print(topCard);
        assertEquals(testDeck.cardsLeft(), 51);
        testDeck.getDeck().clear();
        assertEquals(testDeck.cardsLeft(), 0);
        assertNull(testDeck.drawCard());
    }

    @Test
    public void drawCard() {
        Deck testDeck = new Deck();
        Card topCard = testDeck.drawCard();
        assertNotNull(topCard);
        Card aceHearts = new Card(0,0);
        assertEquals(topCard.toString(), aceHearts.toString());
        assertEquals(testDeck.cardsLeft(), 51);
        topCard = testDeck.drawCard();
        assertEquals(testDeck.cardsLeft(), 50);
        assertEquals(topCard.toString(), new Card(0, 1).toString());


    }

    @Test
    public void testShuffle(){
        Deck testDeck = new Deck();
        testDeck.shuffleDeck();
        assertEquals(testDeck.cardsLeft(), 52);
        Card topCard = testDeck.drawCard();
        Card aceHearts = new Card(0,0);
        assertNotEquals(topCard.toString(), aceHearts.toString());
        assertEquals(testDeck.cardsLeft(), 51);
        testDeck.resetDeck();
        assertEquals(testDeck.cardsLeft(), 52);
        testDeck.shuffleDeck();
        Card newTop = testDeck.drawCard();
        assertNotEquals(topCard, newTop);
    }
    @Test
    public void testReset(){
        Deck testDeck = new Deck();
        Card topCard = testDeck.drawCard();
        Card aceHearts = new Card(0,0);
        assertEquals(testDeck.cardsLeft(), 51);
        assertEquals(topCard.toString(), aceHearts.toString());
        testDeck.resetDeck();
        assertEquals(testDeck.cardsLeft(), 52);
        topCard = testDeck.drawCard();
        assertEquals(topCard.toString(), aceHearts.toString());

    }
}