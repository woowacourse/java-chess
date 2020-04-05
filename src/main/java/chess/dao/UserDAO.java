package chess.dao;

import static chess.util.RepositoryUtil.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import chess.domain.player.User;

public class UserDAO {

    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO user VALUES (?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, user.getName());
        pstmt.executeUpdate();
    }

    public Optional<User> findByUserName(String userName) throws SQLException {
        String query = "SELECT * FROM user WHERE name = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, userName);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next())
            return Optional.empty();

        return Optional.ofNullable(new User(rs.getString("name")));
    }

    public User updateUserNameByUserName(String originalName, String changedName) throws SQLException {
        String query = "UPDATE user SET name = ? WHERE name = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, changedName);
        pstmt.setString(2, originalName);
        int changedColumnCount = pstmt.executeUpdate();

        return findByUserName(changedName).orElse(new User(changedName));
    }

    public boolean deleteUserByUserName(String name) throws SQLException {
        String query = "DELETE FROM user WHERE name = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, name);
        int affectedRowCount = pstmt.executeUpdate();

        return findByUserName(name) == null;
    }
}