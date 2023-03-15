package chess.domain.piece;

import static chess.domain.piece.PieceType.KNIGHT;

import chess.domain.board.Position;

public class Knight extends Piece {

    private static final Knight WHITE = new Knight(Color.WHITE);
    private static final Knight BLACK = new Knight(Color.BLACK);

    private Knight(final Color color) {
        super(color, KNIGHT);
    }

    public static Knight from(final Color color) {
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
