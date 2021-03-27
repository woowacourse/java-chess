package chess.domain.piece;

import java.util.List;

public class Bishop extends UnlimitedMovePiece {

    public Bishop(PieceColor pieceColor) {
        super(PieceType.BISHOP, pieceColor);
    }

    @Override
    public List<Direction> directions() {
        return Direction.diagonalDirection();
    }
}
