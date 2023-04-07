package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private static final List<Rook> blackRooks = new ArrayList<>();
    private static final List<Rook> whiteRooks = new ArrayList<>();
    private static final int NUMBER_OF_ROOKS_EACH_SIDE = 2;

    static {
        addRooks(blackRooks, Color.BLACK);
        addRooks(whiteRooks, Color.WHITE);
    }

    public Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    private static void addRooks(final List<Rook> rooks, final Color color) {
        for (int i = 0; i < NUMBER_OF_ROOKS_EACH_SIDE; i++) {
            rooks.add(new Rook(color));
        }
    }

    public static List<Rook> getRooksOf(final Color color) {
        if (color == Color.BLACK) {
            return List.copyOf(blackRooks);
        }
        return List.copyOf(whiteRooks);
    }

    @Override
    public boolean isValidMove(final Position from, final Position to, final Piece piece) {
        return isNotSameSide(piece) && isLine(from, to);
    }

    private boolean isLine(final Position from, final Position to) {
        from.validateNotSameSquare(to);

        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);
        return isLine(verticalDistance, horizontalDistance);
    }

    private boolean isLine(final int verticalDistance, final int horizontalDistance) {
        return verticalDistance == 0 || horizontalDistance == 0;
    }
}
