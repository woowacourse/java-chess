package chess.piece;

import chess.chessboard.Position;
import chess.chessboard.Rank;
import chess.chessboard.Side;

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

    public Pawn(Side side) {
        super(side, PieceType.PAWN);
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
    public boolean isValidMove(final Position from, final Position to, final Piece pieceAtDestination) {
        return canMoveForward(from, to, pieceAtDestination) ||
                isCatchable(from, to, pieceAtDestination);
    }

    private boolean canMoveForward(final Position from, final Position to, final Piece pieceAtDestination) {
        return pieceAtDestination.isEmpty() && isForwardMovableRange(from, to);
    }

    private boolean isForwardMovableRange(final Position from, final Position to) {
        from.validateNotSameSquare(to);

        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);

        return from.isBackOf(to, getSide()) &&
                isForwardMovableDistance(verticalDistance, horizontalDistance, from);
    }

    private boolean isForwardMovableDistance(final int verticalDistance, final int horizontalDistance, final Position fromPosition) {
        if (isAtInitialPosition(fromPosition)) {
            return horizontalDistance == 0 && (verticalDistance == 1 || verticalDistance == 2);
        }
        return horizontalDistance == 0 && verticalDistance == 1;
    }

    private boolean isAtInitialPosition(final Position position) {
        if (isSideOf(Side.WHITE)) {
            return position.isAtRank(WHITE_PAWN_INITIAL_RANK);
        }
        return position.isAtRank(BLACK_PAWN_INITIAL_RANK);
    }

    private boolean isCatchable(final Position from, final Position to, final Piece piece) {
        return isOppositeSide(piece) && isCatchableRange(from, to);
    }

    private boolean isCatchableRange(final Position from, final Position to) {
        from.validateNotSameSquare(to);

        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);

        return from.isBackOf(to, getSide()) &&
                isCatchableDistance(verticalDistance, horizontalDistance);
    }

    private boolean isCatchableDistance(final int verticalDistance, final int horizontalDistance) {
        return verticalDistance == 1 && horizontalDistance == 1;
    }
}
