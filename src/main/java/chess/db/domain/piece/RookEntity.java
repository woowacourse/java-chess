package chess.db.domain.piece;

import static chess.beforedb.domain.piece.type.PieceType.ROOK;

import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.player.type.TeamColor;

public class RookEntity extends PieceEntity {
    private static final double SCORE = 5;

    public RookEntity(Long id, TeamColor teamColor) {
        super(id, ROOK, teamColor, SCORE, Direction.rookDirections());
    }

    public RookEntity(TeamColor teamColor) {
        super(ROOK, teamColor, SCORE, Direction.rookDirections());
    }
}
