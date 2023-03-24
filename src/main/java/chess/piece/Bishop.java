package chess.piece;

import chess.chessboard.Side;
import chess.chessboard.Square;

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
    public boolean isMovable(final Square source, final Square destination, final Piece piece) {
        return isDiagonal(source, destination) && isNotSameSide(piece);
    }

    private boolean isDiagonal(final Square source, final Square destination) {
        source.validateNotSameSquare(destination);

        final int verticalDistance = source.calculateVerticalDistance(destination);
        final int horizontalDistance = source.calculateHorizontalDistance(destination);

        return isDiagonal(verticalDistance, horizontalDistance);
    }

    private boolean isDiagonal(final int verticalDistance, final int horizontalDistance) {
        return verticalDistance == horizontalDistance;
    }
}
