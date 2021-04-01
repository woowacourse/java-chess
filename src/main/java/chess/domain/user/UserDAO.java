package chess.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import chess.domain.ConnectionUtils;

public class UserDAO {
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO user (nickname) VALUES (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getNickname());
            pstmt.executeUpdate();
        }
    }
}