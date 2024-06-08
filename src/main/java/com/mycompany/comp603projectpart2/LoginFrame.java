
package com.mycompany.comp603projectpart2;


import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private UserDAO userDAO;
    private ScoresDAO scoresDAO;
    private boolean loggedIn;

    public LoginFrame(UserDAO userDAO, ScoresDAO scoresDAO) {
        this.userDAO = userDAO;
        this.scoresDAO = scoresDAO;
        this.loggedIn = false;
        initialize();
    }

    private void initialize() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if (userDAO.loginUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                loggedIn = true;
                dispose();
                showGameFrame();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login credentials.");
            }
        });

        registerButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if (userDAO.isUsernameTaken(username)) {
                JOptionPane.showMessageDialog(this, "You have registered this account.");
            } else {
                userDAO.registerUser(username, password, null);
                JOptionPane.showMessageDialog(this, "Registration successful!");
            }
        });
    }

    private void showGameFrame() {
        Game game = new Game(userDAO, scoresDAO);
        GameFrame gameFrame = new GameFrame(game, userDAO, scoresDAO, this);
        game.setGameFrame(gameFrame);
        game.startGame();
        gameFrame.setVisible(true);
        gameFrame.showLeaderboard();
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
