package chess.dao;

import chess.domain.Team;
import chess.domain.Turn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDao {
    public Turn findTurn(int playerId) throws SQLException, ClassNotFoundException {
        String query = "select  from player where player_id = ?";
        try (Connection con = ConnectionLoader.load();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, playerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    throw new IllegalArgumentException("Turn이 잘못되었습니다.");
                }
                String team = rs.getString("team");
                return new Turn(Team.of(team));
            }
        }
    }
}
