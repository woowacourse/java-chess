package chess.domain.piece;

import static chess.domain.piece.type.PieceType.KING;

import chess.domain.piece.type.Direction;
import chess.domain.player.type.TeamColor;

public class KingEntity extends PieceEntity {
    private static final double SCORE = 0;

    public KingEntity(Long id, TeamColor teamColor) {
        super(id, KING, teamColor, SCORE, Direction.kingDirections());
    }

    public KingEntity(TeamColor teamColor) {
        super(KING, teamColor, SCORE, Direction.kingDirections());
    }
}
