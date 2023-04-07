package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    private static final int ROUTE_LENGTH_OF_LSHAPE = 3;
    private static final int KNIGHTS_COUNT_OF_EACH_SIDE = 2;

    private static final List<Knight> blackKnights = new ArrayList<>();
    private static final List<Knight> whiteKnights = new ArrayList<>();

    static {
        addKnights(blackKnights, Color.BLACK);
        addKnights(whiteKnights, Color.WHITE);
    }

    public Knight(final Color color) {
        super(color, PieceType.KNIGHT);
    }

    private static void addKnights(final List<Knight> Knights, final Color color) {
        for (int i = 0; i < KNIGHTS_COUNT_OF_EACH_SIDE; i++) {
            Knights.add(new Knight(color));
        }
    }

    public static List<Knight> getKnightsOf(final Color color) {
        if (color == Color.BLACK) {
            return List.copyOf(blackKnights);
        }
        return List.copyOf(whiteKnights);
    }

    @Override
    public boolean isValidMove(final Position from, final Position to, final Piece piece) {
        return isNotSameSide(piece) && isLShape(from, to);
    }

    private boolean isLShape(final Position from, final Position to) {
        from.validateNotSameSquare(to);

        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);

        return isLShape(verticalDistance, horizontalDistance);
    }

    private boolean isLShape(final int verticalDistance, final int horizontalDistance) {
        if (verticalDistance == 0 || horizontalDistance == 0) {
            return false;
        }
        return verticalDistance + horizontalDistance == ROUTE_LENGTH_OF_LSHAPE;
    }
}
