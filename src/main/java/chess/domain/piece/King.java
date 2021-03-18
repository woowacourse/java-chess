package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class King extends Piece {
    private static final List<Direction> POSSIBLE_DIRECTIONS = Arrays.asList(Direction.EAST, Direction.WEST, Direction.SOUTH,
            Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHEAST, Direction.SOUTHWEST);
    private static final String INITIAL_NAME = "K";

    public King(final boolean isBlack) {
        super(isBlack, INITIAL_NAME);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Piece piece) {
        return isPossibleDirection(source, target) && (isOpponent(piece) || piece.equals(new Blank()));
    }

    private boolean isPossibleDirection(final Position source, final  Position target) {
        return POSSIBLE_DIRECTIONS.stream()
                .anyMatch(possibleDirection -> possibleDirection.isSameDirection(target.subtract(source)));
    }
}
