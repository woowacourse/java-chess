package chess.domain.dao;

import chess.domain.dto.BoardDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class BoardDao {

    private Connection conn;

    public BoardDto load() throws SQLException {
        conn = ConnectionSetup.getConnection();
        final String query = "SELECT * FROM board";
        final PreparedStatement pstmt = conn.prepareStatement(query);
        final ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            ConnectionSetup.closeConnection(conn);
            return null;
        }

        final String team = rs.getString("team");
        final boolean isGameOver = rs.getBoolean("isGameOver");
        ConnectionSetup.closeConnection(conn);
        return new BoardDto(team, isGameOver);
    }

    public void save(final BoardDto boardDto) throws SQLException {
        deleteAll();
        conn = ConnectionSetup.getConnection();
        final String query = "INSERT INTO board VALUES (?, ?)";
        final PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, boardDto.team());
        pstmt.setBoolean(2, boardDto.isGameOver());
        pstmt.executeUpdate();
        ConnectionSetup.closeConnection(conn);
    }

    public void deleteAll() throws SQLException {
        conn = ConnectionSetup.getConnection();
        final String query = "DELETE FROM board";
        final PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.executeUpdate();
        ConnectionSetup.closeConnection(conn);
    }
}
