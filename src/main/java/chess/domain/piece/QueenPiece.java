package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class QueenPiece extends FullPiece {

    private static final String WHITE_NAME = "q";
    private static final String BLACK_NAME = "Q";

    private final String name;
    private final List<Direction> movableDirections;

    public QueenPiece(final Color color) {
        super(color);
        this.name = decideName(color);
        this.movableDirections = new ArrayList<>(
            List.of(Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH,
                Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST,
                Direction.SOUTH_WEST));
    }

    private String decideName(final Color color) {
        if (color == Color.WHITE) {
            return WHITE_NAME;

        }
        return BLACK_NAME;
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final boolean isEmptyTarget) {
        final int fileDistance = to.calculateFileDistance(from);
        final int rankDistance = to.calculateRankDistance(from);

        final Direction direction = Direction.of(fileDistance, rankDistance);

        return movableDirections.contains(direction);
    }

    @Override
    public String getName() {
        return name;
    }

}
