package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.Team;

public class Rook extends NoneEmptyPiece {

    public Rook(final Team team) {
        super(team, Movement.ROOK);
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
