package blackjack;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScorePanel extends JPanel {
    private JLabel playerLabel;
    private int playerTurn;
    private int numPlayers;
    public ArrayList<Player> players;

    public ScorePanel(ArrayList<Player> players){
        this.players = players;
        numPlayers = players.size();
        playerTurn = 0;
        playerLabel = new JLabel(players.get(playerTurn).getName() + "'s Turn");
        playerLabel.setFont(new Font("Apple Casual", Font.BOLD, 20));
        playerLabel.setForeground(Color.RED);
        add(playerLabel);
        this.setVisible(true);

    }
    public void update(ArrayList<Player> players){
        this.players = players;
        numPlayers = players.size();
        playerTurn = 0;
        playerLabel.setText(players.get(playerTurn).getName() + "'s Turn");

    }
    public void setTurn(int playerTurn){
        this.playerTurn = playerTurn;
    }

    public int nextTurn() {
        playerTurn++;
        if(playerTurn == numPlayers){
            playerLabel.setText("Dealer's Turn");
            return playerTurn;
        }
        playerLabel.setText(players.get(playerTurn).getName() + "'s Turn");
        return playerTurn;
    }

    public void startNewRound() {
        playerTurn = 0;
        playerLabel.setText(players.get(playerTurn).getName() + "'s Turn");
    }
}
