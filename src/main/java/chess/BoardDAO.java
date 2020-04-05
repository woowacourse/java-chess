package chess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

public class BoardDAO {

    private final Connection conn;

    public BoardDAO(Connection conn) {
        this.conn = conn;
    }

    public String getPieceSymbolByPositionSymbol(String positionSymbol) throws SQLException {
        String query = "SELECT * FROM chessboard WHERE position = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, positionSymbol);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        return rs.getString("piece");
    }

    public Board loadBoard() throws SQLException {
        String query = "SELECT * FROM chessboard";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        return makeBoard(rs);
    }

    private Board makeBoard(ResultSet rs) {

        //TODO
        return new Board(Collections.emptyMap());
    }

//    public void addUser(User user) throws SQLException {
//        String query = "INSERT INTO user VALUES (?, ?)";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.setString(1, user.getUserId());
//        pstmt.setString(2, user.getName());
//        pstmt.executeUpdate();
//    }
//
//    public User findByUserId(String userId) throws SQLException {
//        String query = "SELECT * FROM user WHERE user_id = ?";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.setString(1, userId);
//        ResultSet rs = pstmt.executeQuery();
//
//        if (!rs.next()) return null;
//
//        return new User(
//                rs.getString("user_id"),
//                rs.getString("name"));
//    }
//
//    public void updateUser(User user1, User user2) throws SQLException {
//
//    }
//
//    public void deleteByUserId(String userId) throws SQLException {
//        String query = "DELETE FROM user WHERE user_id = ?";
//        PreparedStatement pstmt = getConnection().prepareStatement(query);
//        pstmt.setString(1, userId);
//        pstmt.executeUpdate();
//    }

}
