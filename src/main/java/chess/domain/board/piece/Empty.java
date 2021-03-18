package chess.domain.board.piece;

import chess.domain.board.Position;

public class Empty extends Piece{
    private static final Empty EMPTY = new Empty();

    private Empty() {
        super(Owner.NONE);
    }

    public static Empty getInstance() {
        return EMPTY;
    }

    @Override
    public void validateMove(Position source, Position target, boolean hasEnemy) {
        throw new IllegalArgumentException();
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return ".";
    }
}
