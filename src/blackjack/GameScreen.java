package blackjack;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GameScreen extends JPanel {
    private final String IMAGE_PATH = "src/blackjack/CardImages/";
    public static Map<String, ImageIcon> cardIconMap = new HashMap<>();
    public Game game;
    public Deck deck;
    public ArrayList<Player> players;
    public Map<Player, Integer> bets;
    public Player dealer;
    public ScorePanel scorePanel;
    public ArrayList<PlayerPanel> playerPanels;
    public JPanel cardsPanel;
    public int numPlayers;
    public int playerTurn;

    public GameScreen(int numPlayers){
        this.numPlayers = numPlayers;
        game = new Game(numPlayers);
        playerTurn = 0;
        deck = game.getGameDeck();
        importImages();
        players = game.getPlayers();
        dealer = game.dealer;
        bets = new HashMap<Player, Integer>();
        deck.shuffleDeck();
        game.initialDeal();
        scorePanel = new ScorePanel(players);
        this.setLayout(new BorderLayout());
        this.add(scorePanel, BorderLayout.PAGE_START);
        playerPanels = new ArrayList<>();
        playerPanels.add(new PlayerPanel(dealer));
        cardsPanel = new JPanel();
        JScrollPane scrPane = new JScrollPane(cardsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        for(int countPlayer = 0; countPlayer < numPlayers; countPlayer++){
            playerPanels.add(new PlayerPanel(players.get(countPlayer)));
        }
        for (PlayerPanel playerPanel : playerPanels) {
            cardsPanel.add(playerPanel);
        }
        this.add(scrPane, BorderLayout.CENTER);

        setPreferredSize(new Dimension(850, 800));
        setVisible(true);
//        askForBets();

    }

    public void askForBet(Player player) {
        String bet = JOptionPane.showInputDialog(null,player.getName() + ", how much would you like to bet?");
        if(bet == null){
            bet = "0";
        }
        bets.put(player, Integer.parseInt(bet));

    }

    private void showCards() {
        for (int panelCounter = 0; panelCounter < numPlayers + 1; panelCounter++) {
            playerPanels.get(panelCounter).showCards();
        }
    }

    public void importImages(){
        for(Card c : deck.cardsList){
            cardIconMap.put(c.toImageName(), new ImageIcon(IMAGE_PATH + c.toImageName() + ".png"));
        }
    }

    public void startNewGame(int numPlayers){
        int oldPlayers = this.numPlayers;
        game = new Game(numPlayers);
        this.numPlayers = numPlayers;
        deck = game.getGameDeck();
        players = game.getPlayers();
        playerTurn = 0;
        dealer = game.dealer;
        deck.shuffleDeck();
        game.initialDeal();
        scorePanel.update(players);
        scorePanel.startNewRound();
        int counter = 1;
        playerPanels.get(0).update(dealer);
        for(Player player : players){
            if(counter <= oldPlayers) {
                playerPanels.get(counter).update(player);
            }
            counter++;
        }
        if(oldPlayers > numPlayers){
            Component[] compList = cardsPanel.getComponents();
            for (int clearPanels = oldPlayers; clearPanels > numPlayers; clearPanels--) {
                playerPanels.get(clearPanels).clear();
                cardsPanel.remove(compList[clearPanels]);
            }
        }
        if(oldPlayers < numPlayers){
            for (int newPanels = oldPlayers; newPanels < numPlayers; newPanels++) {
                playerPanels.add(new PlayerPanel(players.get(newPanels)));
                cardsPanel.add(playerPanels.get(playerPanels.size()-1));
            }
        }
        showCards();
    }

    public void updateTurn() {
        playerTurn = scorePanel.nextTurn();
        if(playerTurn == numPlayers){
            boolean dealerStays = game.hitDealer();
            showCards();
            if(!dealerStays){
                JOptionPane.showMessageDialog(null, "Everyone wins!");
                Iterator it = bets.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    Player p = (Player)pair.getKey();
                    if(!p.getIsBust()){
                        p.addMoney((int)pair.getValue());
                    }
                    it.remove(); // avoids a ConcurrentModificationException
                }
            } else if(dealer.isBlackjack()){
                JOptionPane.showMessageDialog(null, "Everyone loses!");
                Iterator it = bets.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    Player p = (Player)pair.getKey();
                    p.addMoney(-1*(int)pair.getValue());
                    it.remove(); // avoids a ConcurrentModificationException
                }
            } else {
                JOptionPane.showMessageDialog(null, "Dealer under 21, payouts being made");
                for(Player player : players){
                    if(game.isWinner(player)){
                        player.addMoney(bets.getOrDefault(player, 0));
                    } else {
                        if(!player.getIsBust()) {
                            player.addMoney(-1*bets.getOrDefault(player, 0));
                        }
                    }
                }
            }
            playerTurn = 0;
            game.startNewRound();
            scorePanel.startNewRound();
            bets.clear();
            showCards();
        }
    }

    public void hitHand() {
        Player currentPlayer = players.get(playerTurn);
        if(!bets.containsKey(currentPlayer)){
            askForBet(currentPlayer);
        }

        ArrayList<Card> playerHand = currentPlayer.getHand();
        game.hitPlayer(currentPlayer);
        showCards();
        int bet = bets.getOrDefault(currentPlayer, 0);
        if(currentPlayer.isBlackjack()){
            JOptionPane.showMessageDialog(null, "Blackjack!");
            updateTurn();
        }
        if(currentPlayer.getIsBust()){
            JOptionPane.showMessageDialog(null, "You have bust, you are out :(");
            currentPlayer.addMoney(-1*bet);
            updateTurn();
        }
        showCards();
    }
}
