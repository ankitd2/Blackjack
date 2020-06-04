package blackjack;

import java.util.ArrayList;

public class Game {
    public Deck gameDeck;
    public int numPlayers;
    public ArrayList<Player> players;
    public Player dealer;

    /**
     * Default constructor setup with 1 dealer and 1 player
     */
    public Game(){
        gameDeck = new Deck();
        numPlayers = 1;
        players = new ArrayList<>();
        for(int playerNo = 0; playerNo < numPlayers; playerNo++){
            players.add(playerNo, new Player(playerNo + 1));
        }
        dealer = new Player(0);
    }

    /**
     * Constructor to set up a new Blackjack with with a specified number of players
     * The dealer is set to Player 0 by default
     * @param numPlayers - number of players in the game
     */
    public Game(int numPlayers){
        gameDeck = new Deck();
        this.numPlayers = numPlayers;
        players = new ArrayList<>();
        for(int playerNo = 0; playerNo < numPlayers; playerNo++){
            players.add(playerNo, new Player(playerNo + 1));
        }
        dealer = new Player(0);
    }

    /**
     * Deals 2 cards in rotation starting with dealer and all players in the game
     */
    public void initialDeal(){
        for (int iterations = 0; iterations < 2; iterations++) {
            dealer.addCard(gameDeck.drawCard());
            for (Player player : players){
                player.addCard(gameDeck.drawCard());
            }
        }
    }

    /**
     * Checks to see if a player can be hit with another card so if score is under 21
     * @param player - Player's hand to be checked
     * @return True - if player can be hit
     *         False - otherwise
     */
    public boolean canHitPlayer(Player player){
        if(player.getIsBust() || player.isBlackjack()){
            return false;
        }
       return true;
    }

    /**
     * Hits a Player with one card but first checks to see if player was already bust or has blackjack
     * @param player
     * @return True - if card added
     *         False - if player busted or deck out of cards
     */
    public boolean hitPlayer(Player player){
        if(canHitPlayer(player)){
            if(gameDeck.cardsLeft() > 0){
                player.addCard(gameDeck.drawCard());
                return true;
            }
        }
        return false;
    }

    /**
     * Deals cards to dealer once all players have hit or stayed with their hands.
     * Returns whether dealer busts or stays between 17 - 21. By rule, dealer must hit
     * until they reach a value of at least 17 then stay.
     * @return true if dealer stays within the limits
     *         false otherwise
     */
    public boolean hitDealer(){
        while(dealer.getHandValue() < 17 && gameDeck.cardsLeft() > 0){
            dealer.addCard(gameDeck.drawCard());
        }
        if(dealer.getIsBust()){
            return false;
        }
        return true;
    }

    /**
     * Gets if the selected player wins that hand of blackjack
     * @param player
     * @return
     */
    public boolean isWinner(Player player){
        if(player.getIsBust()){
            return false;
        }
        if(dealer.getIsBust()){
            return true;
        }
        if(dealer.isBlackjack()){
            return false;
        }
        if(player.isBlackjack()){
            return true;
        }
        if(!player.getIsBust() && (player.getHandValue() > dealer.getHandValue())){
            return true;
        }
        return false;
    }

    public void startNewRound(){
        gameDeck.resetDeck();
        dealer.resetHand();
        for(Player p : players){
            p.resetHand();
        }
        gameDeck.shuffleDeck();
        initialDeal();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Deck getGameDeck() {
        return gameDeck;
    }
}
