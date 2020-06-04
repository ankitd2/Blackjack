package blackjack;

/**
 * Class for each Card in a Deck. Each card has a rank and suit with their values in Blackjack
 * being assignmed as per Blackjack rules. Aces are 11, Face cards are 10, and all other cards have their own values.
 */
public class Card {
    private int suit;
    private int rank;
    private int value;
    private String suitName;
    private String rankName;


    public static String[] suitNames = {"H", "D", "S", "C"};
    public static String[] rankNames = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    /**
     * Constructor for a Card with a given suit and rank
     * @param suit - coded for Suit 0:Hearts, 1:Diamonds, 2:Spades, 3:Clubs
     * @param rank - coded for Rank 0 starting at Ace, 12 ending at King
     */
    public Card(int suit, int rank){
        this.suit = suit;
        this.rank = rank;
        suitName = suitNames[suit];
        rankName = rankNames[rank];
        value = getValue();
    }

    /**
     * Overridden toString method to display the coded values of suit and rank of a card
     * in terms of readability
     * @return String with the form Rank of Suit
     */
    @Override
    public String toString() {
        return getRankName() + " of " + getSuitName();
    }

    public String toImageName(){
        return rankName + suitName;
    }
    /**
     * Getter for the coded integer suit of a card
     * @return int suit of Card according to previous code
     */
    public int getSuit() {
        return suit;
    }

    /**
     * Setter for setting the suit of card between 0 and 3
     * @param suit - new suit of card
     */
    public void setSuit(int suit) {
        if(suit < 0 || suit > 3){
            return;
        }
        this.suit = suit;
        this.suitName = suitNames[suit];
    }

    /**
     * Setter for new rank of a card between 0 and 12
     * @param rank - new rank of card
     */
    public void setRank(int rank) {
        if(rank < 0 || rank > 12){
            return;
        }
        this.rank = rank;
        this.rankName = rankNames[rank];
    }

    /**
     * Getter for rank of a card
     * @return int coded rank of a card
     */
    public int getRank(){
        return rank;
    }

    /**
     * Getter for the value of the card for Blackjack scores
     * @return int score of the card
     */
    public int getValue() {
        if (rank >= 10) {
            return 10;
        } else if (rank == 0) {
            return 11;
        } else {
            return rank + 1;
        }
    }

    /**
     * Getter for the suit of the card translated to English from coded integer
     * @return String the name of the suit of the card
     */
    public String getSuitName() {
        return suitName;
    }

    /**
     * Getter for the rank of the card translated from coded rank
     * @return String the name of the rank of the suit
     */
    public String getRankName() {
        return rankName;
    }

}
