package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import java.util.List;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(PieceType.EMPTY, Color.NONE, null);
    }

    @Override
    public void validateMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition) {
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
