package chess.domain.piece;

import chess.domain.position.Position;

public class Rook extends Piece {

    private static final Rook WHITE = new Rook(Color.WHITE);
    private static final Rook BLACK = new Rook(Color.BLACK);
    private static final int VALID_GAP = 0;

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
        final int fileGap = start.calculateFileGap(end);
        final int rankGap = start.calculateRankGap(end);

        return fileGap == VALID_GAP || rankGap == VALID_GAP;
    }

    @Override
    protected boolean isValidTarget(final Piece target) {
        return color() != target.color();
    }
}
