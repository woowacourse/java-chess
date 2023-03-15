package chess.domain.piece;

import static chess.domain.piece.PieceType.KING;

import chess.domain.board.Position;

public class King extends Piece {

    private static final King WHITE = new King(Color.WHITE);
    private static final King BLACK = new King(Color.BLACK);

    private King(final Color color) {
        super(color, KING);
    }

    public static King from(final Color color) {
        if (color == Color.WHITE) {
            return WHITE;
        }
        return BLACK;
    }

    @Override
    protected boolean isValidMove(final Position start, final Position end) {
        return false;
    }

    @Override
    protected boolean isValidTarget(final Piece target) {
        return false;
    }
}
