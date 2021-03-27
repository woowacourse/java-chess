package chess.db.domain.piece;

import static chess.domain.piece.type.PieceType.BISHOP;

import chess.domain.piece.type.Direction;
import chess.domain.player.type.TeamColor;

public class BishopEntity extends PieceEntity {
    private static final double SCORE = 3;

    public BishopEntity(Long id, TeamColor teamColor) {
        super(id, BISHOP, teamColor, SCORE, Direction.bishopDirections());
    }

    public BishopEntity(TeamColor teamColor) {
        super(BISHOP, teamColor, SCORE, Direction.bishopDirections());
    }
}
