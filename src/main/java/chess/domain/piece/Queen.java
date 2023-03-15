package chess.domain.piece;

import static chess.domain.piece.PieceType.QUEEN;

public class Queen extends Piece {
    public Queen(final Color color) {
        super(color, QUEEN);
    }
}
