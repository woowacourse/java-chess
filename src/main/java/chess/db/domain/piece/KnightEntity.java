package chess.db.domain.piece;

import static chess.beforedb.domain.piece.type.PieceType.KNIGHT;

import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.player.type.TeamColor;

public class KnightEntity extends PieceEntity {
    private static final double SCORE = 2.5;

    public KnightEntity(Long id, TeamColor teamColor) {
        super(id, KNIGHT, teamColor, SCORE, Direction.knightDirections());
    }

    public KnightEntity(TeamColor teamColor) {
        super(KNIGHT, teamColor, SCORE, Direction.knightDirections());
    }
}
