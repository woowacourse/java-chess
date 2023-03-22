package chess.piece;

import chess.chessboard.Rank;
import chess.chessboard.Side;
import chess.chessboard.Square;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {
    private static final int NUMBER_OF_PAWNS_EACH_SIDE = 8;
    private static final Rank WHITE_PAWN_INITIAL_RANK = Rank.TWO;
    private static final Rank BLACK_PAWN_INITIAL_RANK = Rank.SEVEN;

    private static final List<Pawn> blackPawns = new ArrayList<>();
    private static final List<Pawn> whitePawns = new ArrayList<>();

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
    public boolean isMovable(final Square source, final Square destination, final Piece pieceAtDestination) {
        return canMoveForward(source, destination, pieceAtDestination) ||
                isCatchable(source, destination, pieceAtDestination);
    }

    private boolean canMoveForward(final Square source, final Square destination, final Piece pieceAtDestination) {
        return pieceAtDestination.isEmpty() && isForwardMovableRange(source, destination);
    }

    private boolean isForwardMovableRange(final Square source, final Square destination) {
        source.validateNotSameSquare(destination);

        final int verticalDistance = source.calculateVerticalDistance(destination);
        final int horizontalDistance = source.calculateHorizontalDistance(destination);

        return source.isBackOf(destination, getSide()) &&
                isForwardMovableDistance(verticalDistance, horizontalDistance, source);
    }

    private boolean isForwardMovableDistance(final int verticalDistance, final int horizontalDistance, final Square sourceSquare) {
        if (isAtInitialPosition(sourceSquare)) {
            return horizontalDistance == 0 && (verticalDistance == 1 || verticalDistance == 2);
        }
        return horizontalDistance == 0 && verticalDistance == 1;
    }

    private boolean isAtInitialPosition(final Square square) {
        if (isWhite()) {
            return square.isAtRank(WHITE_PAWN_INITIAL_RANK);
        }
        return square.isAtRank(BLACK_PAWN_INITIAL_RANK);
    }

    private boolean isCatchable(final Square source, final Square destination, final Piece piece) {
        return isOppositeSide(piece) && isCatchableRange(source, destination);
    }

    private boolean isCatchableRange(final Square source, final Square destination) {
        source.validateNotSameSquare(destination);

        final int verticalDistance = source.calculateVerticalDistance(destination);
        final int horizontalDistance = source.calculateHorizontalDistance(destination);

        return source.isBackOf(destination, getSide()) &&
                isCatchableDistance(verticalDistance, horizontalDistance);
    }

    private boolean isCatchableDistance(final int verticalDistance, final int horizontalDistance) {
        return verticalDistance == 1 && horizontalDistance == 1;
    }
}
