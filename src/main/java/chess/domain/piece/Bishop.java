package chess.domain.piece;

import static chess.domain.piece.PieceType.BISHOP;

import chess.domain.position.Position;

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

    @Override
    protected boolean isValidMove(final Position start, final Position end) {
        final int fileGap = Math.abs(start.calculateFileGap(end));
        final int rankGap = Math.abs(start.calculateRankGap(end));

        return fileGap == rankGap;
    }

    @Override
    protected boolean isValidTarget(final Piece target) {
        return color() != target.color();
    }
}
