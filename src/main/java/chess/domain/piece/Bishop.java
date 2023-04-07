package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private static final int NUMBER_OF_BISHOPS_EACH_SIDE = 2;

    private static final List<Bishop> blackBishops = new ArrayList<>();
    private static final List<Bishop> whiteBishops = new ArrayList<>();

    static {
        addBishops(blackBishops, Color.BLACK);
        addBishops(whiteBishops, Color.WHITE);
    }

    public Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }

    private static void addBishops(final List<Bishop> bishops, final Color color) {
        for (int i = 0; i < NUMBER_OF_BISHOPS_EACH_SIDE; i++) {
            bishops.add(new Bishop(color));
        }
    }

    public static List<Bishop> getBishopsOf(final Color color) {
        if (color == Color.BLACK) {
            return List.copyOf(blackBishops);
        }
        return List.copyOf(whiteBishops);
    }

    @Override
    public boolean isValidMove(final Position from, final Position to, final Piece piece) {
        return isDiagonal(from, to) && isNotSameSide(piece);
    }

    private boolean isDiagonal(final Position from, final Position to) {
        from.validateNotSameSquare(to);

        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);

        return isDiagonal(verticalDistance, horizontalDistance);
    }

    private boolean isDiagonal(final int verticalDistance, final int horizontalDistance) {
        return verticalDistance == horizontalDistance;
    }
}
