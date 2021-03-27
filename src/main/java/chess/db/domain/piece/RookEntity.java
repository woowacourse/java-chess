package chess.db.domain.piece;

import static chess.domain.piece.type.PieceType.ROOK;

import chess.domain.piece.type.Direction;
import chess.domain.player.type.TeamColor;

public class RookEntity extends PieceEntity {
    private static final double SCORE = 5;

    public RookEntity(Long id, TeamColor teamColor) {
        super(id, ROOK, teamColor, SCORE, Direction.rookDirections());
    }

    public RookEntity(TeamColor teamColor) {
        super(ROOK, teamColor, SCORE, Direction.rookDirections());
    }
}
