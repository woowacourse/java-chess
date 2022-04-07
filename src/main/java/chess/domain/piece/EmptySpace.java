package chess.domain.piece;

import chess.domain.board.Position;
import java.util.List;

public class EmptySpace extends Piece {

    public EmptySpace() {
        super(PieceType.NONE, Color.NONE);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public List<Position> calculatePathToValidate(Position current, Position target, Piece targetPiece) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Direction findValidDirection(Position current, Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void validateDirection(Direction direction) {
        throw new UnsupportedOperationException();
    }
}
