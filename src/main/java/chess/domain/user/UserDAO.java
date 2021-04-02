package chess.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.ConnectionUtils;

public class UserDAO {
    public void addUser(User user) throws SQLException {
        String findQuery = "SELECT name FROM user WHERE name = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(findQuery)) {
            pstmt.setString(1, user.getName());
            if (!pstmt.executeQuery().first()) {
                String query = "INSERT INTO user (name) VALUES (?)";
                PreparedStatement pstmtTwo = connection.prepareStatement(query);
                pstmtTwo.setString(1, user.getName());
                pstmtTwo.executeUpdate();
            }
        }
    }

    public Long findIdByName(String name) throws SQLException {
        String query = "SELECT user_id FROM user u WHERE u.name = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("user_id");
            }
        }
        throw new IllegalArgumentException("존재하지 않는 이름입니다.");
    }
}