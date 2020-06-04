package blackjack;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {
    public ArrayList<Card> cards;
    private JButton[] cardButtons;
    public Player player;
    private JLabel nameLabel;
    private JLabel scoreLabel;

    public int MAX_CARDS = 5;


    public PlayerPanel(Player player){
        this.player = player;
        this.cards = player.getHand();
        cardButtons = new JButton[MAX_CARDS];
        nameLabel = new JLabel(player.getName() + ": $" + player.getMoney());
        nameLabel.setFont(new Font("Apple Casual", Font.PLAIN, 14));
        add(nameLabel, BorderLayout.PAGE_START);


        for (int cardIterator = 0; cardIterator < MAX_CARDS; cardIterator++) {
            JButton card = new JButton();
            paintButton(new Color(0, 86, 31), card);
            cardButtons[cardIterator] = card;
            add(card);
        }

        scoreLabel = new JLabel(" Score : " + player.getHandValue());
        scoreLabel.setFont(new Font("Apple Casual", Font.PLAIN, 14));
        add(scoreLabel, BorderLayout.PAGE_END);

        setLayout(new GridLayout(1, 5));
//        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.GRAY));
        setVisible(true);
        showCards();
    }
    /**
     * This is used to setup the display for the squares of the chess board. The JButton[][] is set with alternating
     * colors of dark green and light green and the background color of the button is set to display the square tile.
     * @param color the desired color of the button
     * @param button JButton which will have color changed
     */
    private void paintButton(Color color, JButton button) {
        button.setBackground(color);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }
    public void showCards(){
        for (int cardIter = 0; cardIter < MAX_CARDS; cardIter++) {
            cardButtons[cardIter].setIcon(null);
        }
        for (int cardIter = 0; cardIter < cards.size(); cardIter++) {
            cardButtons[cardIter].setIcon(null);
            Image image = GameScreen.cardIconMap.get(cards.get(cardIter).toImageName()).getImage(); // transform it
            Image newimg = image.getScaledInstance(-1, 160,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            cardButtons[cardIter].setIcon(new ImageIcon(newimg));
        }
        scoreLabel.setText(" Score : " + player.getHandValue());
        nameLabel.setText(player.getName() + ": $" + player.getMoney());


    }

    public void update(Player newPlayer){
        this.player = newPlayer;
        this.cards = this.player.getHand();
        nameLabel.setText(player.getName() + ": $" + player.getMoney());


    }

    public void clear(){
        player = null;
        cards.clear();
        nameLabel = null;
    }
}
