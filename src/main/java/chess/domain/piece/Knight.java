package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;

public class Knight extends NoneEmptyPiece {

    public Knight(final Team team) {
        super(team, Movement.KNIGHT);
    }

    @Override
    public boolean isKnight() {
        return true;
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
