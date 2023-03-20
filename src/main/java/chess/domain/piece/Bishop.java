package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;

public class Bishop extends NoneEmptyPiece {

    public Bishop(final Team team) {
        super(team, Movement.BISHOP);
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
