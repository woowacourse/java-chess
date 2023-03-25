package chess.piece;

import chess.chessboard.Side;
import chess.chessboard.Square;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    private static final int ROUTE_LENGTH_OF_LSHAPE = 3;
    private static final int KNIGHTS_COUNT_OF_EACH_SIDE = 2;

    private static final List<Knight> blackKnights = new ArrayList<>();
    private static final List<Knight> whiteKnights = new ArrayList<>();

    static {
        addKnights(blackKnights, Side.BLACK);
        addKnights(whiteKnights, Side.WHITE);
    }

    private Knight(final Side side) {
        super(side, PieceType.KNIGHT);
    }

    private static void addKnights(final List<Knight> Knights, final Side side) {
        for (int i = 0; i < KNIGHTS_COUNT_OF_EACH_SIDE; i++) {
            Knights.add(new Knight(side));
        }
    }

    public static List<Knight> getKnightsOf(final Side side) {
        if (side == Side.BLACK) {
            return List.copyOf(blackKnights);
        }
        return List.copyOf(whiteKnights);
    }

    @Override
    public boolean isMovable(final Square source, final Square destination, final Piece piece) {
        return isNotSameSide(piece) && isLShape(source, destination);
    }

    private boolean isLShape(final Square source, final Square destination) {
        source.validateNotSameSquare(destination);

        final int verticalDistance = source.calculateVerticalDistance(destination);
        final int horizontalDistance = source.calculateHorizontalDistance(destination);

        return isLShape(verticalDistance, horizontalDistance);
    }

    private boolean isLShape(final int verticalDistance, final int horizontalDistance) {
        if (verticalDistance == 0 || horizontalDistance == 0) {
            return false;
        }
        return verticalDistance + horizontalDistance == ROUTE_LENGTH_OF_LSHAPE;
    }
}
