package chess.domain.piece;

import java.util.List;

public class Queen extends UnlimitedMovePiece {

    public Queen(PieceColor pieceColor) {
        super(PieceType.QUEEN, pieceColor);
    }

    @Override
    public List<Direction> directions() {
        return Direction.everyDirection();
    }
}
