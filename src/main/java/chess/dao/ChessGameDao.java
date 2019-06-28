package chess.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao {
    private static final String QUERY_FOR_ADD_GAME = "INSERT INTO game VALUES(?, 'WHITE')";
    private static final String QUERY_FOR_FIND_TURN = "SELECT turn FROM game WHERE id=?";
    private static final String QUERY_FOR_UPDATE_TURN = "UPDATE game SET turn=? WHERE id=1";
    private static final String QUERY_FOR_DELETE_GAME = "DELETE FROM game";

    private final DataSource dataSource;

    public ChessGameDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addGame(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERY_FOR_ADD_GAME);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public String findTurn(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERY_FOR_FIND_TURN);
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) return null;
                return rs.getString("turn");
            }
        }
    }

    public void updateTurn(String nextTurn) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERY_FOR_UPDATE_TURN);
            pstmt.setString(1, nextTurn);
            pstmt.executeUpdate();
        }
    }

    public void deleteGame() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(QUERY_FOR_DELETE_GAME);
            pstmt.executeUpdate();
        }
    }
}
