package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import java.util.List;

public class EmptyPiece extends Piece {

    private static final String NOTATION = ".";

    public EmptyPiece() {
        super(Color.NONE, null);
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

    @Override
    public String getNotation() {
        return NOTATION;
    }
}
