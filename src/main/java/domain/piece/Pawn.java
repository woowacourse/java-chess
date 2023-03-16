package domain.piece;

import domain.Square;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Pawn extends Piece {

    public static final Vectorr TOP = Vectorr.of(0, 1);
    public static final Vectorr TOP_DOUBLE = Vectorr.of(0, 2);
    public static final Vectorr TOP_RIGHT = Vectorr.of(1, 1);
    public static final Vectorr TOP_LEFT = Vectorr.of(-1, 1);

    public static final Vectorr BOTTOM = Vectorr.of(0, -1);
    public static final Vectorr BOTTOM_DOUBLE = Vectorr.of(0, -2);
    public static final Vectorr BOTTOM_RIGHT = Vectorr.of(1, -1);
    public static final Vectorr BOTTOM_LEFT = Vectorr.of(-1, -1);

    private static List<Vectorr> directions;

    private State state;

    public Pawn(TeamColor teamColor) {
        super(teamColor);
        initDirection();
        state = State.INIT;
    }

    private void initDirection() {
        if (isBlack()) {
            directions = List.of(BOTTOM, BOTTOM_DOUBLE, BOTTOM_RIGHT, BOTTOM_LEFT);
            return;
        }
        directions = List.of(TOP, TOP_DOUBLE, TOP_RIGHT, TOP_LEFT);
    }

    @Override
    public List<Square> findRoutes(Square src, Square dest) {
        Vectorr vector = dest.calculateVector(src);
        Optional<Vectorr> direction = findDirection(vector);

        if (direction.isEmpty()) {
            return Collections.emptyList();
        }

        return getSquares(src, dest, vector);
    }

    private Optional<Vectorr> findDirection(Vectorr vector) {
        return directions.stream()
            .filter(direction -> direction.equals(vector))
            .findAny();
    }

    private List<Square> getSquares(Square src, Square dest, Vectorr vector) {
        if (vector.equals(TOP_DOUBLE)) {
            return List.of(src.add(TOP), src.add(TOP_DOUBLE));
        }
        if (vector.equals(BOTTOM_DOUBLE)) {
            return List.of(src.add(BOTTOM), src.add(BOTTOM_DOUBLE));
        }
        return List.of(dest);
    }

    public void start() {
        state = State.RUNNING;
        if (isBlack()) {
            directions = List.of(BOTTOM, BOTTOM_RIGHT, BOTTOM_LEFT);
            return;
        }
        directions = List.of(TOP, TOP_RIGHT, TOP_LEFT);
    }
}
