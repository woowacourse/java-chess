package chess.domain.piece;

import static chess.domain.piece.PieceType.PAWN;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color, PAWN);
    }
}
