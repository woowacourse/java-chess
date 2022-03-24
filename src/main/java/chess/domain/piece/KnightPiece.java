package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class KnightPiece extends FullPiece {

    private static final String WHITE_NAME = "n";
    private static final String BLACK_NAME = "N";

    private final String name;
    private final List<Direction> movableDirections;

    public KnightPiece(final Color color) {
        super(color);
        this.name = decideName(color);
        this.movableDirections = new ArrayList<>(
            List.of(Direction.KNIGHT_EAST_LEFT, Direction.KNIGHT_EAST_RIGHT,
                Direction.KNIGHT_WEST_LEFT, Direction.KNIGHT_WEST_RIGHT,
                Direction.KNIGHT_NORTH_LEFT, Direction.KNIGHT_NORTH_RIGHT,
                Direction.KNIGHT_SOUTH_LEFT,
                Direction.KNIGHT_SOUTH_RIGHT));
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final boolean isEmptyTarget) {
        final int fileDistance = to.calculateFileDistance(from);
        final int rankDistance = to.calculateRankDistance(from);

        final Direction direction = Direction.of(fileDistance, rankDistance);

        return movableDirections.contains(direction);
    }

    private String decideName(final Color color) {
        if (color == Color.WHITE) {
            return WHITE_NAME;
        }
        return BLACK_NAME;
    }

    @Override
    public String getName() {
        return name;
    }
}
