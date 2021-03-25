package chess.domain.piece;

import java.util.List;

public class King extends LimitedMovePiece {

    public King(PieceColor pieceColor) {
        super(PieceType.KING, pieceColor);
    }

    @Override
    public List<Direction> directions() {
        return Direction.everyDirection();
    }
}
