package blackjack;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    public String name;
    public boolean isBust;
    public int money;

    /**
     * Constructs a Player object with name Player + playerNum. Initializes the hand of the player to be empty.
     * @param playerNum
     */
    public Player(int playerNum){

        hand = new ArrayList<Card>();
        name = "Player " + playerNum;
        isBust = false;
        money = 500;
        if(playerNum == 0){
            name = "Dealer";
            money = 500000;
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Constructs a Player object with given name. Initializes the hand of the player to be empty.
     * @param name
     */
    public Player(String name){
        this.name = name;
        hand = new ArrayList<Card>();
        isBust = false;
        money = 500;
    }

    /**
     * Getter for the name of the Player
     * @return String name of the Player
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the Player's current hand
     * @return ArrayList player's hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Adds the given card to the player's hand. Also checks if the added card would make the player bust.
     * @param newCard Card that will be added to the hand
     * @return ArrayList player's updated hand with the newCard added
     */
    public ArrayList<Card> addCard(Card newCard){
        if(isBust == false) {
            hand.add(newCard);
        }
        isBust = (getHandValue() > 21);
        return hand;
    }

    /**
     * Gets the score of all of the player's cards in their hand
     * @return int sum of the cards' values
     */
    public int getHandValue(){
        int sum = 0;
        if(hand.size() > 0) {
            for (Card c : hand) {
                sum += c.getValue();
            }
        }
        return sum;
    }

    /**
     * Resets a player's hand for the next round by clearing their pre-existing hand to empty and resetting isBust to false.
     */
    public void resetHand(){
        hand.clear();
        isBust = false;
    }

    /**
     * Getter for isBust if the player's hand will bust according to Blackjack rules - have a sum over 21.
     * @return true if player's hand has a value over 21
     *         false if still under 21
     */
    public boolean getIsBust(){
        return isBust;
    }

    /**
     * Checks to see if the player has a blackjack - sum of exactly 21
     * @return true if player has a blackjack
     */
    public boolean isBlackjack(){
        return getHandValue() == 21;
    }

    /**
     * Checks if a player is still eligible to be hit for another card if their hand has a value under 21.
     * @return true if player can add another card
     */
    public boolean canHit(){
        return getHandValue() < 21;
    }

    public void addMoney(int newMoney) {
        money += newMoney;
    }
}
