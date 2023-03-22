package chess.domain.piece;

import static chess.domain.piece.PieceType.QUEEN;

import chess.domain.position.Position;

public class Queen extends Piece {

    private static final Queen WHITE = new Queen(Color.WHITE);
    private static final Queen BLACK = new Queen(Color.BLACK);
    private static final int VALID_GAP = 0;

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
        final int fileGap = Math.abs(start.calculateFileGap(end));
        final int rankGap = Math.abs(start.calculateRankGap(end));

        return canMoveStraight(fileGap, rankGap) || canMoveDiagonal(fileGap, rankGap);
    }

    private boolean canMoveStraight(final int fileGap, final int rankGap) {
        return fileGap == VALID_GAP || rankGap == VALID_GAP;
    }

    private boolean canMoveDiagonal(final int fileGap, final int rankGap) {
        return fileGap == rankGap;
    }

    @Override
    protected boolean isValidTarget(final Piece target) {
        return color() != target.color();
    }
}
