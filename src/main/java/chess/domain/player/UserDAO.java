package chess.domain.player;

import chess.repository.RepositoryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private final Connection connection;

    public UserDAO() {
        this.connection = RepositoryUtil.getConnection();
    }

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO user VALUES (?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, user.getName());
        pstmt.executeUpdate();
    }

    public User findByUserName(String userName) throws SQLException {
        String query = "SELECT * FROM user WHERE name = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, userName);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        return new User(
                rs.getString("name"));
    }

    public User updateUserNameByUserName(String originalName, String changedName) throws SQLException {
        String query = "UPDATE user SET name = ? WHERE name = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, changedName);
        pstmt.setString(2, originalName);
        int changedColumnCount = pstmt.executeUpdate();

        return findByUserName(changedName);
    }

    public boolean deleteUserByUserName(String name) throws SQLException {
        String query = "DELETE FROM user WHERE name = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, name);
        int affectedRowCount = pstmt.executeUpdate();

        return affectedRowCount > 0;
    }
}
