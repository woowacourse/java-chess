package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.List;

public class PawnPiece extends FullPiece {

    private static final String WHITE_NAME = "p";
    private static final String BLACK_NAME = "P";

    private final String name;
    private final List<Direction> movableDirections;

    public PawnPiece(final Color color) {
        super(color);
        this.name = decideName(color);
        this.movableDirections = decideMovableDirections(color);
    }

    private List<Direction> decideMovableDirections(final Color color) {
        if (color == Color.WHITE) {
            return new ArrayList<>(
                List.of(Direction.NORTH, Direction.NORTH_EAST, Direction.NORTH_WEST));
        }
        return new ArrayList<>(
            List.of(Direction.SOUTH, Direction.SOUTH_EAST, Direction.SOUTH_WEST));
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        final int fileDistance = to.calculateFileDistance(from);
        final int rankDistance = to.calculateRankDistance(from);

        final Direction direction = Direction.of(fileDistance, rankDistance);

        if (isInitialPositionAndMoveTwice(from, fileDistance, rankDistance)) {
            return true;
        }

        return movableDirections.contains(direction) &&
            Math.abs(fileDistance) < 2 && Math.abs(rankDistance) < 2;
    }

    private boolean isInitialPositionAndMoveTwice(final Position from, final int fileDistance, final int rankDistance) {
        if (super.getColor() == Color.BLACK && from.isSameRank(Rank.SEVEN) && fileDistance == 0 && rankDistance == -2) {
            return true;
        }
        return super.getColor() == Color.WHITE && from.isSameRank(Rank.TWO) && fileDistance == 0 && rankDistance == 2;
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
