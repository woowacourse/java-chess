package chess.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.ConnectionUtils;

public class UserDAO {
    public void addUser(User user) throws SQLException {
        String findQuery = "SELECT nickname FROM user WHERE nickname = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(findQuery)) {
            pstmt.setString(1, user.getNickname());
            if (!pstmt.executeQuery().first()) {
                String query = "INSERT INTO user (nickname) VALUES (?)";
                PreparedStatement pstmtTwo = connection.prepareStatement(query);
                pstmtTwo.setString(1, user.getNickname());
                pstmtTwo.executeUpdate();
            }
        }
    }
}