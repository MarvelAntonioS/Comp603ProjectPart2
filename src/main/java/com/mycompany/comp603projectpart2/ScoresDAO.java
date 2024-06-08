
package com.mycompany.comp603projectpart2;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScoresDAO {
    public void recordScore(int userId, int score) {
        String sql = "INSERT INTO Scores (user_id, score) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getTopPlayers() {
        List<String> topPlayers = new ArrayList<>();
        String sql = "SELECT u.username, s.score FROM Scores s JOIN Users u ON s.user_id = u.id ORDER BY s.score DESC FETCH FIRST 10 ROWS ONLY";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                topPlayers.add(rs.getString("username") + ": " + rs.getInt("score"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topPlayers;
    }
}
