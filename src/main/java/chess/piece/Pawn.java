package chess.piece;

import chess.chessboard.Rank;
import chess.chessboard.Side;
import chess.chessboard.Square;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {
    private static final int NUMBER_OF_PAWNS_EACH_SIDE = 8;

    private static final List<Pawn> blackPawns = new ArrayList<>();
    private static final List<Pawn> whitePawns = new ArrayList<>();
    private static final Rank WHITE_PAWN_INITIAL_RANK = Rank.TWO;
    private static final Rank BLACK_PAWN_INITIAL_RANK = Rank.SEVEN;

    static {
        addPawns(blackPawns, Side.BLACK);
        addPawns(whitePawns, Side.WHITE);
    }

    private Pawn(Side side) {
        super(side);
    }

    private static void addPawns(final List<Pawn> pawns, final Side side) {
        for (int i = 0; i < NUMBER_OF_PAWNS_EACH_SIDE; i++) {
            pawns.add(new Pawn(side));
        }
    }

    public static List<Pawn> getPawnsOf(final Side side) {
        if (side == Side.BLACK) {
            return List.copyOf(blackPawns);
        }
        return List.copyOf(whitePawns);
    }

    @Override
    public boolean isMovable(final Square source, final Square destination, final Piece piece) {
        return canMoveForward(source, destination, piece) ||
                isCatchable(source, destination, piece);
    }

    private boolean canMoveForward(final Square source, final Square destination, final Piece destinationState) {
        boolean isTargetInMovableRange = isMovableRange(source, destination);
        if (isAtInitialPosition(source)) {
            isTargetInMovableRange = isInitialMovableRange(source, destination);
        }
        return destinationState.isEmpty() && isTargetInMovableRange;
    }

    private boolean isInitialMovableRange(final Square source, final Square destination) {
        source.validateNotSameSquare(destination);
        final int verticalDistance = source.calculateVerticalDistance(destination);
        final int horizontalDistance = source.calculateHorizontalDistance(destination);
        return source.isBackOf(destination, getSide()) &&
                (verticalDistance == 1 || verticalDistance == 2) &&
                horizontalDistance == 0;
    }

    private boolean isAtInitialPosition(final Square square) {
        if (isWhite()) {
            return square.isAtRank(WHITE_PAWN_INITIAL_RANK);
        }
        return square.isAtRank(BLACK_PAWN_INITIAL_RANK);
    }

    private boolean isMovableRange(final Square source, final Square destination) {
        source.validateNotSameSquare(destination);
        final int verticalDistance = source.calculateVerticalDistance(destination);
        return source.isBackOf(destination, getSide()) &&
                verticalDistance == 1 &&
                source.calculateHorizontalDistance(destination) == 0;
    }

    private boolean isCatchable(final Square source, final Square destination, final Piece piece) {
        return isOppositeSide(piece) && isCatchableRange(source, destination);
    }

    private boolean isCatchableRange(final Square source, final Square destination) {
        source.validateNotSameSquare(destination);
        final int verticalDistance = source.calculateVerticalDistance(destination);
        final int horizontalDistance = source.calculateHorizontalDistance(destination);
        return source.isBackOf(destination, getSide()) &&
                verticalDistance == 1 &&
                horizontalDistance == 1;
    }
}
