
package com.mycompany.comp603projectpart2;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameFrame extends JFrame {
    public JPanel chosenCasePanel;
    public JLabel chosenCaseLabel;
    private JLabel promptLabel;
    private CasePanel casePanel;
    private MoneyPanel moneyPanel;
    private BankerPanel bankerPanel;
    private Game currentGame;
    private UserDAO userDAO;
    private ScoresDAO scoresDAO;
    private LoginFrame loginFrame;

    public GameFrame(Game currentGame) {
        this.currentGame = currentGame;
        initialize();
    }

    public GameFrame(Game currentGame, UserDAO userDAO, ScoresDAO scoresDAO, LoginFrame loginFrame) {
        DatabaseConnection.setupDatabase();
        this.currentGame = currentGame;
        this.userDAO = userDAO;
        this.scoresDAO = scoresDAO;
        this.loginFrame = loginFrame;
        initialize();
    }

    private void initialize() {
        setTitle("DEAL OR NO DEAL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);

        // Title
        JLabel titleLabel = new JLabel("DEAL OR NO DEAL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.YELLOW);
        add(titleLabel, BorderLayout.NORTH);

        // Money panel on the left
        moneyPanel = new MoneyPanel(currentGame.caseSet.moneyValues);
        add(moneyPanel, BorderLayout.WEST);

        // Cases panel in the center
        casePanel = new CasePanel(new CaseButtonListener());
        add(casePanel, BorderLayout.CENTER);

        // Banker panel on the right
        bankerPanel = new BankerPanel(new DealButtonListener(), new NoDealButtonListener());
        add(bankerPanel, BorderLayout.EAST);

        // Chosen case panel at the bottom
        chosenCasePanel = new JPanel();
        chosenCasePanel.setLayout(new BorderLayout());
        chosenCaseLabel = new JLabel("Your Case: ");
        chosenCaseLabel.setFont(new Font("Arial", Font.BOLD, 16));
        chosenCasePanel.add(chosenCaseLabel, BorderLayout.WEST);

        promptLabel = new JLabel("Choose a case to start the game", SwingConstants.CENTER);
        promptLabel.setFont(new Font("Arial", Font.BOLD, 18));
        promptLabel.setForeground(Color.BLACK);
        chosenCasePanel.add(promptLabel, BorderLayout.CENTER);

        add(chosenCasePanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void setPrompt(String prompt) {
        promptLabel.setText(prompt);
    }

    public void setChosenCaseLabel(int caseNumber) {
        chosenCaseLabel.setText("Your Case: " + caseNumber);
    }

    public void updateMoneyLabel(int moneyValue) {
        moneyPanel.markOffMoney(moneyValue);
    }

    public void disableCaseButton(int index) {
        casePanel.disableButton(index);
    }

    public void updateBankerOffer(int offer) {
        bankerPanel.updateOfferLabel(offer);
    }

    public void enableDealButtons() {
        bankerPanel.enableButtons();
    }

    public void disableAllCaseButtons() {
        casePanel.disableAllButtons();
    }

    public void enableAllCaseButtons() {
        casePanel.enableAllButtons();
    }

    public void disableDealButtons() {
        bankerPanel.disableButtons();
    }

    private class CaseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!loginFrame.isLoggedIn()) {
                JOptionPane.showMessageDialog(GameFrame.this, "Please log in first!");
                return;
            }
            if (!currentGame.isDealing) {
                JButton button = (JButton) e.getSource();
                int caseIndex = Integer.parseInt(button.getText());
                currentGame.caseChosen(caseIndex);
            }
        }
    }

    private class DealButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!loginFrame.isLoggedIn()) {
                JOptionPane.showMessageDialog(GameFrame.this, "Please log in first!");
                return;
            }
            int offer = bankerPanel.getCurrentOffer();
            currentGame.acceptDeal(offer);
        }
    }

    private class NoDealButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!loginFrame.isLoggedIn()) {
                JOptionPane.showMessageDialog(GameFrame.this, "Please log in first!");
                return;
            }
            currentGame.rejectDeal();
            currentGame.isDealing = false;
        }
    }

    public void showLeaderboard() {
        JFrame leaderboardFrame = new JFrame("Leaderboard");
        leaderboardFrame.setSize(600, 400);
        leaderboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        leaderboardFrame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Leaderboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        leaderboardFrame.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Rank", "Player"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        List<String> topPlayers = scoresDAO.getTopPlayers();
        int rank = 1;
        for (String player : topPlayers) {
            String[] parts = player.split(" - ", 2);
            String username = parts.length > 0 ? parts[0] : "Unknown";
            String score = parts.length > 1 ? parts[1] : "0";
            model.addRow(new Object[]{rank, username, score});
            rank++;
        }

        JTable leaderboardTable = new JTable(model);
        leaderboardTable.setFillsViewportHeight(true);
        leaderboardTable.setEnabled(false);
        leaderboardTable.setFont(new Font("Arial", Font.PLAIN, 16));
        leaderboardTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        leaderboardFrame.add(scrollPane, BorderLayout.CENTER);

        leaderboardFrame.setVisible(true);
    }
}
