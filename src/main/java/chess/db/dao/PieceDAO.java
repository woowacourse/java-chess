package chess.db.dao;


import static chess.db.dao.DBConnection.getConnection;

import chess.db.domain.piece.PieceEntity;
import chess.beforedb.domain.piece.type.PieceType;
import chess.beforedb.domain.player.type.TeamColor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PieceDAO {

    public PieceEntity findByPieceTypeAndTeamColor(PieceType pieceType, TeamColor teamColor)
        throws SQLException {
        ResultSet rs = getResultSet(pieceType, teamColor);
        if (rs == null) {
            return null;
        }
        return PieceEntity.castedFrom(
            new PieceFromDB(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("color")));
    }

    private ResultSet getResultSet(PieceType pieceType, TeamColor teamColor) throws SQLException {
        String query = "SELECT * FROM piece WHERE name = ? AND color = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, pieceType.name());
        pstmt.setString(2, teamColor.getValue());
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return rs;
    }
}
