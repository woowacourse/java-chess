package chess.db.dao;


import static chess.db.dao.DBConnection.getConnection;

import chess.db.entity.PieceEntity;
import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PieceDAO {

    public PieceEntity findByPieceTypeAndTeamColor(PieceType pieceType, TeamColor teamColor)
        throws SQLException {
        String query = "SELECT * FROM piece WHERE name = ? AND color = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, pieceType.name());
        pstmt.setString(2, teamColor.getValue());
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return new PieceEntity(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("color"));
    }
}
