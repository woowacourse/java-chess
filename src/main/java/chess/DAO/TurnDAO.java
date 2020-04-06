package chess.DAO;

import chess.piece.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDAO {

    private final Connection conn;

    public TurnDAO(Connection conn) {
        this.conn = conn;
    }

    public Team loadTurn() throws SQLException {
        String query = "SELECT * FROM turn";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        return rs.getBoolean("isWhite") ? Team.WHITE : Team.BLACK;
    }

    public void saveTurn(Team turn) throws SQLException {
        String query = "UPDATE turn SET isWhite = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setBoolean(1, turn.isWhite());
        pstmt.executeUpdate();
    }
}
