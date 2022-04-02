package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.Position;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(PieceType.EMPTY, Color.NONE);
    }

    @Override
    public void validateMove(Board board, Position sourcePosition, Position targetPosition) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isBlack() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
