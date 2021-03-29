package chess.domain.piece;

import static chess.domain.piece.type.PieceType.KNIGHT;

import chess.domain.piece.type.Direction;
import chess.domain.player.type.TeamColor;

public class KnightEntity extends PieceEntity {
    private static final double SCORE = 2.5;

    public KnightEntity(Long id, TeamColor teamColor) {
        super(id, KNIGHT, teamColor, SCORE, Direction.knightDirections());
    }

    public KnightEntity(TeamColor teamColor) {
        super(KNIGHT, teamColor, SCORE, Direction.knightDirections());
    }
}
