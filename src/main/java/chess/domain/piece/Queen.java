package chess.domain.piece;

import static chess.domain.piece.PieceType.QUEEN;

import chess.domain.board.Position;

public class Queen extends Piece {

    private static final Queen WHITE = new Queen(Color.WHITE);
    private static final Queen BLACK = new Queen(Color.BLACK);

    private Queen(final Color color) {
        super(color, QUEEN);
    }

    public static Queen from(final Color color) {
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
