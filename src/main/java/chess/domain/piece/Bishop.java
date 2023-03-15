package chess.domain.piece;

import static chess.domain.piece.PieceType.BISHOP;

public class Bishop extends Piece {

    private static final Bishop WHITE = new Bishop(Color.WHITE);
    private static final Bishop BLACK = new Bishop(Color.BLACK);

    private Bishop(final Color color) {
        super(color, BISHOP);
    }

    public static Bishop from(final Color color) {
        if (color == Color.WHITE) {
            return WHITE;
        }
        return BLACK;
    }
}
