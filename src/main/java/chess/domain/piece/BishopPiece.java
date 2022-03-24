package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class BishopPiece extends FullPiece {

    private static final String WHITE_NAME = "b";
    private static final String BLACK_NAME = "B";

    private final String name;
    private final List<Direction> movableDirections;

    public BishopPiece(final Color color) {
        super(color);
        this.name = decideName(color);
        this.movableDirections = new ArrayList<>(
            List.of(Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST,
                Direction.SOUTH_WEST));
    }

    @Override
    public boolean isMovable(Position from, Position to) {
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
