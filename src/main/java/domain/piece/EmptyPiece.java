package domain.piece;

import domain.position.Position;
import java.util.Map;

public class EmptyPiece extends Piece {

    private static final String NAME = ".";

    public EmptyPiece() {
        super(NAME);
    }

    @Override
    public boolean canMove(Map<Position, Piece> board, Position source, Position target) {
        return false;
    }

    @Override
    public boolean isNotEmpty() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
