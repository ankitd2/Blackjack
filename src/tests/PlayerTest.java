package tests;

import blackjack.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void addCard() {
        Deck testDeck = new Deck();
        Player p1 = new Player(1);
        p1.addCard(testDeck.drawCard());
        assertEquals(p1.getHand().size(), 1);
        Card aceHearts = new Card(0,0);
        assertEquals(p1.getHand().get(0).toString(), aceHearts.toString());
        testDeck.shuffleDeck();
        p1.addCard(testDeck.drawCard());
        assertEquals(p1.getHand().size(), 2);
    }

    @Test
    public void getPlayer() {
        Deck testDeck = new Deck();
        Player p1 = new Player(1);

        assertEquals(p1.getName(), "Player 1");
        assertEquals(p1.getHand().size(), 0);

        Player p2 = new Player("Bob");
        assertEquals(p2.getName(), "Bob");

        Player p3 =  new Player(3);
        assertEquals(p3.getName(), "Player 3");

    }

    @Test
    public void getValue() {
        Deck testDeck = new Deck();
        Player p1 = new Player(1);
        assertEquals(p1.getHandValue(), 0);
        p1.addCard(testDeck.drawCard());
        p1.addCard(testDeck.drawCard());
        assertEquals(p1.getHand().size(), 2);
        assertEquals(p1.getHandValue(), 13);

    }

    @Test
    public void resetHand() {
        Deck testDeck = new Deck();
        Player p1 = new Player(1);
        p1.addCard(testDeck.drawCard());
        p1.addCard(testDeck.drawCard());
        assertEquals(p1.getHand().size(), 2);
        p1.resetHand();
        assertEquals(p1.getHand().size(), 0);

    }

    @Test
    public void testShuffleHand(){
        Deck testDeck = new Deck();
        Player p1 = new Player(1);
        testDeck.shuffleDeck();
        Card firstCard = testDeck.drawCard();
        int handSum = firstCard.getValue();
        p1.addCard(firstCard);
        assertEquals(handSum, p1.getHandValue());
        Card secondCard = testDeck.drawCard();
        handSum += secondCard.getValue();
        p1.addCard(secondCard);
        assertEquals(handSum, p1.getHandValue());
    }

    @Test
    public void testPlayerSuccess(){
        Deck testDeck = new Deck();
        Player p1 = new Player(1);
        p1.addCard(testDeck.drawCard());
        p1.addCard(testDeck.drawCard());
        assertFalse(p1.isBlackjack());
        assertFalse(p1.getIsBust());
        assertTrue(p1.canHit());
    }
}