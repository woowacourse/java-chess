package chess.domain.dao;

import chess.domain.dto.BoardDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class BoardDao {

    private final Connection conn;

    public BoardDao() {
        conn = ConnectionSetup.getConnection();
    }

    public BoardDto load() throws SQLException {
        final String query = "SELECT * FROM board";
        final PreparedStatement pstmt = conn.prepareStatement(query);
        final ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        return new BoardDto(rs.getString("team"), rs.getBoolean("isGameOver"));
    }

    public void save(final BoardDto boardDto) throws SQLException {
        deleteAll();
        final String query = "INSERT INTO board VALUES (?, ?)";
        final PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, boardDto.team());
        pstmt.setBoolean(2, boardDto.isGameOver());
        pstmt.executeUpdate();
    }

    public void deleteAll() throws SQLException {
        final String query = "DELETE FROM board";
        final PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.executeUpdate();
    }
}
