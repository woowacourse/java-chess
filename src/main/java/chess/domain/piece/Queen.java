package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.Team;

public class Queen extends NoneEmptyPiece {

    public Queen(final Team team) {
        super(team, Movement.QUEEN);
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
