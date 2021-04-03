package chess.dao.piece;

import chess.domain.piece.Piece;
import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;
import java.sql.SQLException;

public interface PieceRepository {
    Piece findByPieceTypeAndTeamColor(PieceType pieceType, TeamColor teamColor) throws SQLException;
}
