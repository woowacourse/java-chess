package domain.piece;

import domain.board.InitialChessAlignment;
import domain.position.Position;

public final class Bishop extends Piece {

    private static final String NAME = "B";

    public Bishop(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        return source.isDiagonal(destination);
    }
}
