package chess.piece;

import chess.board.Position;
import java.util.Set;

public final class Blank extends Piece {

    public Blank(final Position position) {
        super(Color.EMPTY, position);
    }

    @Override
    protected Set<Direction> directions() {
        return Set.of();
    }

    @Override
    protected Set<Position> legalMovePositions(final Direction direction, final Pieces pieces) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Piece update(final Position destination) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char symbol() {
        return PieceType.EMPTY.symbol();
    }
}
