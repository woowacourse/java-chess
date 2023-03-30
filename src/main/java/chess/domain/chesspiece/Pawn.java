package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public class Pawn extends Piece {
    private static final Pawn BLACK_PAWN = new Pawn(Side.BLACK);
    private static final Pawn WHITE_PAWN = new Pawn(Side.WHITE);

    Pawn(Side side) {
        super(side, PieceInfo.PAWN);
    }

    public static Pawn from(final Side side) {
        if (side == Side.BLACK) {
            return BLACK_PAWN;
        }
        return WHITE_PAWN;
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return canMoveForward(from, to, piece) ||
                isCatchable(from, to, piece);
    }

    private boolean canMoveForward(final Square from, final Square to, final Piece piece) {
        boolean targetInMovableRange = isPawnsMovableRange(from, to);
        return targetInMovableRange && piece.isEmpty();
    }

    private boolean isPawnsMovableRange(final Square from, final Square to) {
        final int rankDistance = from.rankDistanceTo(to);
        return isBackOf(from, to) &&
                rankDistance == 1 &&
                from.fileDistanceTo(to) == 0;
    }

    private boolean isCatchable(final Square from, final Square to, final Piece piece) {
        return isOppositeSide(piece) && isPawnsCatchableRange(from, to);
    }

    private boolean isPawnsCatchableRange(final Square from, final Square to) {
        final int rankDistance = from.rankDistanceTo(to);
        return isBackOf(from, to) &&
                rankDistance == 1 &&
                from.fileDistanceTo(to) == 1;
    }

    private boolean isBackOf(final Square from, final Square to) {
        if (side == Side.WHITE) {
            return to.hasBiggerRankThan(from);
        }
        return from.hasBiggerRankThan(to);
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
