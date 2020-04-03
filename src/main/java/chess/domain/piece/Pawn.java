package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private static final int START_BLACK_Y = 6;
    private static final int START_WHITE_Y = 1;

    public Pawn(Position position, Color color) {
        super(position, color, Symbol.PAWN);
    }

    private boolean isStarting() {
        if (getPosition().equalsY(START_BLACK_Y) && isBlack()) {
            return true;
        }
        return getPosition().equalsY(START_WHITE_Y) && !isBlack();
    }

    private List<Direction> directions() {
        if (isBlack()) {
            return Direction.BLACK_PAWN_DIRECTION;
        }
        return Direction.WHITE_PAWN_DIRECTION;
    }

    @Override
    protected List<Direction> movableDirections(Piece piece) {
        List<Direction> directions = new ArrayList<>(directions());
        if (piece.isBlank()) {
            directions.remove(Direction.NORTH_EAST);
            directions.remove(Direction.NORTH_WEST);
            directions.remove(Direction.SOUTH_EAST);
            directions.remove(Direction.SOUTH_WEST);
        }
        if (!piece.isBlank()) {
            directions.remove(Direction.NORTH);
            directions.remove(Direction.SOUTH);
        }
        return directions;
    }

    @Override
    protected Direction findDirection(int x, int y) {
        if (isStarting()) {
            return Direction.ofStart(x, y);
        }
        return Direction.of(x, y);
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
