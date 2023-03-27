package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;


public class InitialPawn extends Piece {
    private static final InitialPawn BLACK_INITIAL_PAWN = new InitialPawn(Side.BLACK);
    private static final InitialPawn WHITE_INITIAL_PAWN = new InitialPawn(Side.WHITE);

    private InitialPawn(Side side) {
        super(side, PieceInfo.INITIAL_PAWN);
    }

    public static InitialPawn from(final Side side) {
        if (side == Side.BLACK) {
            return BLACK_INITIAL_PAWN;
        }
        return WHITE_INITIAL_PAWN;
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return canMoveForward(from, to, piece) ||
                isCatchable(from, to, piece);
    }

    private boolean canMoveForward(final Square from, final Square to, final Piece piece) {
        boolean targetInMovableRange = isPawnsInitialMovableRange(from, to);
        return targetInMovableRange && piece.isEmpty();
    }

    private boolean isPawnsInitialMovableRange(final Square from, final Square to) {
        final int rankDistance = from.rankDistanceTo(to);
        return isBackOf(from, to) &&
                (rankDistance == 1 || rankDistance == 2) &&
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
}
