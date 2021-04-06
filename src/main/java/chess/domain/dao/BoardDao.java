package chess.domain.dao;

import chess.domain.dto.BoardDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class BoardDao {

    public BoardDto load() throws SQLException {
        final String query = "SELECT * FROM board";

        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query);
            final ResultSet rs = pstmt.executeQuery()) {
            if (!rs.next()) {
                return null;
            }

            final String team = rs.getString("team");
            final boolean isGameOver = rs.getBoolean("isGameOver");
            return new BoardDto(team, isGameOver);
        }
    }

    public void save(final String team, final boolean isGameOver) throws SQLException {
        final String query = "INSERT INTO board VALUES (?, ?)";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, team);
            pstmt.setBoolean(2, isGameOver);
            pstmt.executeUpdate();
        }
    }

    public void updateTeam(final String team) throws SQLException {
        final String query = "UPDATE board SET team = ? WHERE isGameOver = false";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, team);
            pstmt.executeUpdate();
        }
    }

    public void updateIsGameOver() throws SQLException {
        final String query = "UPDATE board SET isGameOver = true";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.executeUpdate();
        }
    }

    public void deleteAll() throws SQLException {
        final String query = "TRUNCATE TABLE board";
        try (final Connection conn = ConnectionSetup.getConnection();
            final PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.executeUpdate();
        }
    }
}
