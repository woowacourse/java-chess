package chess.domain.piece;

import chess.domain.board.Position;

public class Rook extends Piece {

    private static final Rook WHITE = new Rook(Color.WHITE);
    private static final Rook BLACK = new Rook(Color.BLACK);

    private Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    public static Rook from(final Color color) {
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
