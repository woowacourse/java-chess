package domain.piece;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import domain.Square;

public class Pawn extends Piece {
    public static final Vector TOP = Vector.of(0, 1);
    public static final Vector TOP_DOUBLE = Vector.of(0, 2);
    public static final Vector TOP_RIGHT = Vector.of(1, 1);
    public static final Vector TOP_LEFT = Vector.of(-1, 1);

    public static final Vector BOTTOM = Vector.of(0, -1);
    public static final Vector BOTTOM_DOUBLE = Vector.of(0, -2);
    public static final Vector BOTTOM_RIGHT = Vector.of(1, -1);
    public static final Vector BOTTOM_LEFT = Vector.of(-1, -1);

    private static final PieceType PAWN = PieceType.PAWN;
    private List<Vector> directions;

    private PawnState state;

    public Pawn(TeamColor teamColor) {
        super(teamColor);
        initDirection();
        state = PawnState.INIT;
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
        Vector vector = dest.calculateVector(src);
        Optional<Vector> direction = findDirection(vector);

        if (direction.isEmpty()) {
            return Collections.emptyList();
        }

        return getSquares(src, dest, vector);
    }

    private Optional<Vector> findDirection(Vector vector) {
        return directions.stream()
                .filter(direction -> direction.equals(vector))
                .findAny();
    }

    private List<Square> getSquares(Square src, Square dest, Vector vector) {
        if (vector.equals(TOP_DOUBLE)) {
            return List.of(src.add(TOP), src.add(TOP_DOUBLE));
        }
        if (vector.equals(BOTTOM_DOUBLE)) {
            return List.of(src.add(BOTTOM), src.add(BOTTOM_DOUBLE));
        }
        return List.of(dest);
    }

    public void start() {
        state = PawnState.RUNNING;
        if (isBlack()) {
            directions = List.of(BOTTOM, BOTTOM_RIGHT, BOTTOM_LEFT);
            return;
        }
        directions = List.of(TOP, TOP_RIGHT, TOP_LEFT);
    }

    public boolean isDiagonal(Square src, Square dest) {
        Vector vector = src.calculateVector(dest);
        List<Vector> diagonalDirections = List.of(TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT);
        return diagonalDirections.contains(vector);
    }

    @Override
    public double score() {
        return PAWN.getValue();
    }

    @Override
    public PieceType pieceType() {
        return PAWN;
    }
}
