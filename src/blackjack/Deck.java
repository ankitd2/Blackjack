package blackjack;


import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;
    public ArrayList<Card> cardsList = new ArrayList<>();
    public int numSuits = 4;
    public int numRanks = 13;

    /**
     * Default constructor for 52 unique cards in a deck. First creates a list of cards
     * then fills the current deck with them.
     */
    public Deck(){
        deck = new ArrayList<>();
        for (int suitNum = 0; suitNum < numSuits; suitNum++) {
            for (int rankNum = 0; rankNum < numRanks; rankNum++) {
                Card nextCard = new Card(suitNum, rankNum);
                cardsList.add(nextCard);
            }
        }
        fillDeck();
    }

    /**
     * Fills the ArrayList of Cards representing the deck with all 52 initial cards
     */
    public void fillDeck() {
        for(Card card : cardsList){
            deck.add(card);
        }
    }

    /**
     * Shuffles the ArrayList of Cards in place in a random order
     */
    public void shuffleDeck(){
        Collections.shuffle(this.getDeck());
    }

    /**
     * Getter for the ArrayList of Cards storing the deck
     * @return ArrayList deck of cards
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Gets the top card from the deck and removes it from the deck
     * @return Card the top card from the deck that was removed
     *         null if deck is empty
     */
    public Card drawCard(){
        if(deck.size() > 0) {
            return deck.remove(0);
        } else {
            return null;
        }
    }

    /**
     * Gets the number of cards left in the deck
     * @return int number of cards left in the deck
     */
    public int cardsLeft(){
        return deck.size();
    }

    /**
     * Resets the order of the deck and refills them with the initial 52 cards
     */
    public void resetDeck(){
        deck.clear();
        fillDeck();
    }
}
