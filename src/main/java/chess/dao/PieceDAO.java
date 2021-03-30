package chess.dao;


import static chess.dao.setting.DBConnection.getConnection;

import chess.dao.entity.PieceEntity;
import chess.domain.piece.Piece;
import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PieceDAO {

    public Piece findByPieceTypeAndTeamColor(PieceType pieceType, TeamColor teamColor)
        throws SQLException {
        ResultSet rs = getResultSet(pieceType, teamColor);
        if (rs == null) {
            return null;
        }
        return Piece.castedFrom(
            new PieceEntity(
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
