package tests;

import blackjack.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {


    @Test
    public void getSuit() {
        Card testCard = new Card(0,0);
        assertEquals(testCard.getSuit(), 0);
        assertEquals(testCard.getSuitName(), Card.suitNames[testCard.getSuit()]);
        testCard.setSuit(3);
        assertEquals(testCard.getSuit(), 3);
        assertEquals(testCard.getSuitName(), Card.suitNames[testCard.getSuit()]);
        testCard.setSuit(10);
        assertEquals(testCard.getSuit(), 3);
        assertEquals(testCard.getRank(), 0);
    }

    @Test
    public void getValue() {
        Card testAce = new Card(0, 0);
        assertEquals(testAce.getValue(), 11);
        Card testKing= new Card(0, 12);
        assertEquals(testKing.getValue(), 10);
        testKing.setRank(11);
        assertEquals(testKing.getValue(), 10);
        testKing.setRank(10);
        assertEquals(testKing.getValue(), 10);
        testKing.setRank(1);
        assertEquals(testKing.getValue(), 2);
        testKing.setRank(-5);
        assertEquals(testKing.getValue(), 2);
        testKing.setRank(25);
        assertEquals(testKing.getValue(), 2);

    }

    @Test
    public void getString(){
        Card testCard = new Card(1, 10);
        assertEquals(testCard.toString(), "J of D");
    }
}
