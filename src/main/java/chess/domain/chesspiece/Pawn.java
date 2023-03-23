package chess.domain.chesspiece;

import chess.domain.File;
import chess.domain.Side;
import chess.domain.Square;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Pawn extends Piece {
    private static final List<Pawn> BLACK_PAWNS = Arrays.stream(File.values())
            .map(file -> new Pawn(Side.BLACK))
            .collect(Collectors.toUnmodifiableList());
    private static final List<Pawn> WHITE_PAWNS = Arrays.stream(File.values())
            .map(file -> new Pawn(Side.WHITE))
            .collect(Collectors.toUnmodifiableList());

    private boolean atInitialPosition;

    private Pawn(Side side) {
        super(side, PieceInfo.PAWN);
        atInitialPosition = true;
    }

    public static List<Pawn> of(final Side side) {
        if (side == Side.BLACK) {
            return List.copyOf(BLACK_PAWNS);
        }
        return List.copyOf(WHITE_PAWNS);
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return canMoveForward(from, to, piece) ||
                isCatchable(from, to, piece);
    }

    private boolean canMoveForward(final Square from, final Square to, final Piece piece) {
        boolean targetInMovableRange = isPawnsMovableRange(from, to);
        if (atInitialPosition) {
            targetInMovableRange = isPawnsInitialMovableRange(from, to);
        }
        return targetInMovableRange && piece.isEmpty();
    }

    private boolean isPawnsInitialMovableRange(final Square from, final Square to) {
        final int rankDistance = from.rankDistanceTo(to);
        return isBackOf(from, to) &&
                (rankDistance == 1 || rankDistance == 2) &&
                from.fileDistanceTo(to) == 0;
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

    public void move() {
        atInitialPosition = false;
    }
}
