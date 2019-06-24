package chess.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao {

    private final DataSource dataSource;

    public ChessGameDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addGame() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "INSERT INTO game VALUES(1, 'white')";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        }
    }

    public String findTurn() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT turn FROM game WHERE id=1";
            PreparedStatement pstmt = conn.prepareStatement(query);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) return null;
                return rs.getString("turn");
            }
        }
    }

    public void deleteGame() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "DELETE FROM game";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        }
    }



}
