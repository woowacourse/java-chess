package chess.domain.piece;

import java.util.List;

public class Knight extends LimitedMovePiece {

    public Knight(PieceColor pieceColor) {
        super(PieceType.KNIGHT, pieceColor);
    }

    @Override
    public List<Direction> directions() {
        return Direction.knightDirection();
    }
}
