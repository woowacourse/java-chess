package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;

public class King extends NoneEmptyPiece {

    public King(final Team team) {
        super(team, Movement.KING);
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
