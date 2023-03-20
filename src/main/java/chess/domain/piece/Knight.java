package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.Team;

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
