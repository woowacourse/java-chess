package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;

public class Queen extends NoneEmptyPiece {

    public Queen(final Team team) {
        super(team, Movement.QUEEN);
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
