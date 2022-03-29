package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import java.util.List;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(PieceType.EMPTY, Color.NONE);
    }

    @Override
    public void validateMove(List<List<Piece>> board, Position source, Position target) {
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
