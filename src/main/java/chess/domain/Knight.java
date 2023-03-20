package chess.domain;

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
        super(side);
    }

    private static void addKnights(final List<Knight> Knights, final Side side) {
        for (int i = 0; i < KNIGHTS_COUNT_OF_EACH_SIDE; i++) {
            Knights.add(new Knight(side));
        }
    }

    public static List<Knight> of(final Side side) {
        if (side == Side.BLACK) {
            return List.copyOf(blackKnights);
        }
        return List.copyOf(whiteKnights);
    }

    @Override
    boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isNotSameSide(piece) && isLShape(from, to);
    }

    public boolean isLShape(final Square from, final Square to) {
        from.validateNotSameSquare(to);
        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);
        if (verticalDistance == 0 || horizontalDistance == 0) {
            return false;
        }
        return verticalDistance + horizontalDistance == ROUTE_LENGTH_OF_LSHAPE;
    }
}
