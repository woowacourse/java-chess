package chess.dao;

import chess.DBConnector;
import chess.piece.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {

    private final Connection conn;

    public TurnDao() {
        this.conn = DBConnector.getConnection();
    }

    public Team load() throws SQLException {
        String query = "SELECT * FROM turn";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        return rs.getBoolean("isWhite") ? Team.WHITE : Team.BLACK;
    }

    public void save(Team turn) throws SQLException {
        String query = "UPDATE turn SET isWhite = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setBoolean(1, turn.isWhite());
        pstmt.executeUpdate();
    }
}
