package chess.domain.piece;

import static chess.domain.piece.PieceType.KNIGHT;

public class King extends Piece {

    public King(final Color color) {
        super(color, KNIGHT);
    }
}
