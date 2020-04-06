package chess;

import chess.piece.Piece;
import chess.position.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
        return new Board(toPieces(rs));
    }

    private Map<Position, Piece> toPieces(ResultSet rs) throws SQLException {
        Map<Position, Piece> pieces = new HashMap<>();
        Position position;
        Piece piece;
        do {
            position = Position.of(rs.getString("position"));
            char symbol = rs.getString("piece").charAt(0);
            boolean hasMoved = rs.getBoolean("hasMoved");
            System.out.println(String.format("받아온 값 위치:%s, 심볼:%c, 움직였는지:%b", position.getKey(), symbol, hasMoved));
            piece = Piece.of(symbol, hasMoved);
            System.out.println(String.format("받아온 값으로 만든 Piece값 심볼:%c, 움직였는지:%b", piece.getSymbol(), piece.getHasMoved()));
            pieces.put(position, piece);
        } while (rs.next());

        return pieces;
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
