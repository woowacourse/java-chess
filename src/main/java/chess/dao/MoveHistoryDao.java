package chess.dao;

import chess.domains.piece.PieceColor;
import chess.domains.position.Position;
import chess.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MoveHistoryDao {
    private final Connection conn;

    public MoveHistoryDao() {
        this.conn = JdbcUtil.getConnection();
    }

    public void addMoveHistory(String user_id, PieceColor team, Position source, Position target) throws SQLException {
        String query = "INSERT INTO MOVE_HISTORY VALUES (?, (SELECT IFNULL(MAX(MOVES) + 1, 1) FROM MOVE_HISTORY AS INNERTABLE WHERE USER_ID = ?), ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, user_id);
        pstmt.setString(2, user_id);
        pstmt.setString(3, team.name());
        pstmt.setString(4, source.name());
        pstmt.setString(5, target.name());
        pstmt.executeUpdate();
    }

    public Optional<String> figureLastTurn(String user_id) throws SQLException {
        String query = "SELECT TEAM FROM MOVE_HISTORY WHERE USER_ID = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, user_id);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) return Optional.empty();
        return Optional.ofNullable(rs.getString("TEAM"));
    }

}
