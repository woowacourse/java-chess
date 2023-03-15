package chess.domain.piece;

import static chess.domain.piece.PieceType.KING;

public class King extends Piece {

    public King(final Color color) {
        super(color, KING);
    }
}
