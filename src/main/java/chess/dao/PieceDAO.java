package chess.dao;


import chess.dao.entity.PieceEntity;
import chess.domain.piece.Piece;
import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PieceDAO {

    public Piece findByPieceTypeAndTeamColor(PieceType pieceType, TeamColor teamColor) throws SQLException {
        String query = "SELECT * FROM piece WHERE name = ? AND color = ?";
        ResultSet resultSet = SQLQuery.select(query, pieceType.name(), teamColor.getValue());
        if (!resultSet.next()) {
            return null;
        }
        return Piece.of(new PieceEntity(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getString("color")));
    }
}
