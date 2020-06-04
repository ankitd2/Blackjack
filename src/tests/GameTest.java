package tests;

import org.junit.Test;
import blackjack.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void initialGameSetup() {
        Game testGame = new Game();
        assertEquals(testGame.numPlayers, 1);
        testGame.initialDeal();
        Player dealer = testGame.dealer;
        ArrayList<Player> players = testGame.getPlayers();
        assertEquals(dealer.getHand().size(), 2);
        for (Player player: players) {
            assertEquals(player.getHand().size(), 2);
        }
        Game testGame4Players = new Game(4);
        assertEquals(testGame4Players.numPlayers, 4);
    }

    @Test
    public void hitPlayer() {
        Game testGame = new Game();
        testGame.initialDeal();
        Player dealer = testGame.dealer;
        ArrayList<Player> players = testGame.getPlayers();
        System.out.println("Dealer " + dealer.getHand() + " " + dealer.getHandValue());
        for (Player p: players) {
            System.out.println(p.getName() + " " + p.getHand() + " " + p.getHandValue());
        }
        Player p1 = players.get(0);
        assertTrue(testGame.hitPlayer(p1));
        System.out.println(p1.getName() + " " + p1.getHand() + " " + p1.getHandValue());
        assertTrue(testGame.hitPlayer(p1));
        System.out.println(p1.getName() + " " + p1.getHand() + " " + p1.getHandValue());
        assertTrue(testGame.hitPlayer(p1));
        System.out.println(p1.getName() + " " + p1.getHand() + " " + p1.getHandValue());
        assertFalse(testGame.hitPlayer(p1));
        System.out.println(p1.getName() + " " + p1.getHand() + " " + p1.getHandValue());

        testGame.startNewRound();
        assertEquals(p1.getHand().size(), 2);
        assertEquals(dealer.getHand().size(), 2);
        assertEquals(testGame.getGameDeck().cardsLeft(), 48);

        testGame.getGameDeck().shuffleDeck();
        testGame.initialDeal();
        System.out.println("Dealer " + dealer.getHand() + " " + dealer.getHandValue());
        for (Player p: players) {
            System.out.println(p.getName() + " " + p.getHand() + " " + p.getHandValue());
        }


    }
    @Test
    public void isLoserBust() {
        Game testGame = new Game();
        testGame.initialDeal();
        Player dealer = testGame.dealer;
        ArrayList<Player> players = testGame.getPlayers();
        System.out.println("Dealer " + dealer.getHand() + " " + dealer.getHandValue());
        for (Player p: players) {
            System.out.println(p.getName() + " " + p.getHand() + " " + p.getHandValue());
        }
        Player p1 = players.get(0);
        testGame.hitPlayer(p1);
        System.out.println(p1.getName() + " " + p1.getHand() + " " + p1.getHandValue());
        testGame.hitPlayer(p1);
        System.out.println(p1.getName() + " " + p1.getHand() + " " + p1.getHandValue());
        testGame.hitPlayer(p1);
        testGame.hitPlayer(p1);
        System.out.println(p1.getName() + " " + p1.getHand() + " " + p1.getHandValue());
        assertTrue(p1.getIsBust());
        assertFalse(testGame.isWinner(p1));
    }
    @Test
    public void isWinnerBust() {
        Game testGame = new Game();
        testGame.initialDeal();
        Player dealer = testGame.dealer;
        ArrayList<Player> players = testGame.getPlayers();
        System.out.println("Dealer " + dealer.getHand() + " " + dealer.getHandValue());
        for (Player p: players) {
            System.out.println(p.getName() + " " + p.getHand() + " " + p.getHandValue());
        }
        Player p1 = players.get(0);
        testGame.hitPlayer(p1);
        System.out.println(p1.getName() + " " + p1.getHand() + " " + p1.getHandValue());
        testGame.hitPlayer(p1);
        System.out.println(p1.getName() + " " + p1.getHand() + " " + p1.getHandValue());
        System.out.println(p1.getName() + " " + p1.getHand() + " " + p1.getHandValue());
        assertFalse(p1.getIsBust());
        assertTrue(testGame.hitDealer());
        System.out.println("Dealer " + dealer.getHand() + " " + dealer.getHandValue());
        assertFalse(testGame.isWinner(p1));
    }
    @Test
    public void isDealerBust() {
        Game testGame = new Game(3);
        testGame.initialDeal();
        Player dealer = testGame.dealer;
        ArrayList<Player> players = testGame.getPlayers();
        System.out.println("Dealer " + dealer.getHand() + " " + dealer.getHandValue());
        for (Player p: players) {
            System.out.println(p.getName() + " " + p.getHand() + " " + p.getHandValue());
        }
        assertFalse(testGame.hitDealer());
        for (Player p: players) {
            assertTrue(testGame.isWinner(p));
        }

    }

}