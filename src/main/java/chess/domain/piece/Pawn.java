package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.strategy.PieceType;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {
    private static final int NUMBER_OF_PAWNS_EACH_SIDE = 8;
    private static final Rank WHITE_PAWN_INITIAL_RANK = Rank.TWO;
    private static final Rank BLACK_PAWN_INITIAL_RANK = Rank.SEVEN;

    private static final List<Pawn> blackPawns = new ArrayList<>();
    private static final List<Pawn> whitePawns = new ArrayList<>();

    static {
        addPawns(blackPawns, Color.BLACK);
        addPawns(whitePawns, Color.WHITE);
    }

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    private static void addPawns(final List<Pawn> pawns, final Color color) {
        for (int i = 0; i < NUMBER_OF_PAWNS_EACH_SIDE; i++) {
            pawns.add(new Pawn(color));
        }
    }

    public static List<Pawn> getPawnsOf(final Color color) {
        if (color == Color.BLACK) {
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
        if (isSideOf(Color.WHITE)) {
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
