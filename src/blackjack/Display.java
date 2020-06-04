package blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JPanel {
    private JPanel rootPanel;
    private GameScreen gameScr;

    public Display() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        this.add(rootPanel);

//        ScorePanel scorePanel = new ScorePanel(isBlackTurn);
//        rootPanel.add(scorePanel, BorderLayout.PAGE_START);

        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new FlowLayout());
        rootPanel.add(commandPanel, BorderLayout.PAGE_END);

        // creates a start button with a green text color
        JButton hitBtn = new JButton("Hit");
        hitBtn.setFont(new Font("Calibri", Font.PLAIN, 16));
        hitBtn.setForeground(Color.RED);
        hitBtn.setPreferredSize(new Dimension(100,35));
        commandPanel.add(hitBtn);

        JButton stayBtn = new JButton("Stay");
        stayBtn.setFont(new Font("Calibri", Font.PLAIN, 16));
        stayBtn.setForeground(new Color(0, 204, 0));
        stayBtn.setPreferredSize(new Dimension(100,35));
        commandPanel.add(stayBtn);


        // creates a start button with a green text color
        JButton startBtn = new JButton("Start");
        startBtn.setFont(new Font("Calibri", Font.PLAIN, 16));
        startBtn.setPreferredSize(new Dimension(100,35));
        commandPanel.add(startBtn);

//        creates a reset button with a red text color
        JButton betBtn = new JButton("Place Bet");
        betBtn.setFont(new Font("Calibri", Font.PLAIN, 16));
        betBtn.setPreferredSize(new Dimension(100,35));
        commandPanel.add(betBtn);


        JButton helpBtn = new JButton("Help");
        helpBtn.setFont(new Font("Calibri", Font.PLAIN, 16));
        helpBtn.setForeground(new Color(0, 130, 255));
        helpBtn.setPreferredSize(new Dimension(100,35));
        commandPanel.add(helpBtn);

        gameScr = new GameScreen(3);
        rootPanel.add(gameScr, BorderLayout.CENTER);
//        this.setPreferredSize(new Dimension(1080, 1050));
        hitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gameScr.hitHand();
            }
        });
        stayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gameScr.updateTurn();
            }
        });
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String playerNum = JOptionPane.showInputDialog("How many players would you like in your game?");
                gameScr.startNewGame(Integer.parseInt(playerNum));
            }
        });
        betBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gameScr.askForBet(gameScr.players.get(gameScr.playerTurn));
//                JOptionPane.showMessageDialog(null, "Dealer under 21, payouts being made");

            }
        });

        helpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null ,"To Learn Blackjack Rules, visit Documentation");
            }
        });

    }

    private Container getRootPanel() {
        return rootPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Blackjack Display");
        Display disp = new Display();
        frame.setContentPane(disp.getRootPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}