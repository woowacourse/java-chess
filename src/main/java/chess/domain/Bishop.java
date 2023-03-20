package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private static final int NUMBER_OF_BISHOPS_EACH_SIDE = 2;

    private static final List<Bishop> blackBishops = new ArrayList<>();
    private static final List<Bishop> whiteBishops = new ArrayList<>();

    static {
        addBishops(blackBishops, Side.BLACK);
        addBishops(whiteBishops, Side.WHITE);
    }

    private Bishop(final Side side) {
        super(side);
    }

    private static void addBishops(final List<Bishop> bishops, final Side side) {
        for (int i = 0; i < NUMBER_OF_BISHOPS_EACH_SIDE; i++) {
            bishops.add(new Bishop(side));
        }
    }

    public static List<Bishop> getBishopsOf(final Side side) {
        if (side == Side.BLACK) {
            return List.copyOf(blackBishops);
        }
        return List.copyOf(whiteBishops);
    }

    @Override
    boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isDiagonal(from, to) && isNotSameSide(piece);
    }

    private boolean isDiagonal(final Square from, final Square to) {
        from.validateNotSameSquare(to);
        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);
        return verticalDistance == horizontalDistance;
    }
}
