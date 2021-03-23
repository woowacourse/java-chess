package chess.domain.piece;

import java.util.List;

public class Rook extends Piece {

    public Rook(PieceColor pieceColor) {
        super(PieceType.ROOK, pieceColor);
    }

    public List<Direction> directions() {
        return Direction.straightDirection();
    }
}
