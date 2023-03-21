package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;

public class Rook extends NoneEmptyPiece {

    public Rook(final Team team) {
        super(team, Movement.ROOK);
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
