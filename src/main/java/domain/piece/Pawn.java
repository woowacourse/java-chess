package domain.piece;

import domain.Direction;
import domain.Score;

import java.util.*;
import java.util.stream.IntStream;

public class Pawn extends Piece {
    private static final Score SCORE = new Score(1);
    public static final Map<Position, Pawn> PAWNS = new HashMap<Position, Pawn>() {{
        put(Position.of("a7"), Pawn.of("P", true));
        put(Position.of("b7"), Pawn.of("P", true));
        put(Position.of("c7"), Pawn.of("P", true));
        put(Position.of("d7"), Pawn.of("P", true));
        put(Position.of("e7"), Pawn.of("P", true));
        put(Position.of("f7"), Pawn.of("P", true));
        put(Position.of("g7"), Pawn.of("P", true));
        put(Position.of("h7"), Pawn.of("P", true));
        put(Position.of("a2"), Pawn.of("p", false));
        put(Position.of("b2"), Pawn.of("p", false));
        put(Position.of("c2"), Pawn.of("p", false));
        put(Position.of("d2"), Pawn.of("p", false));
        put(Position.of("e2"), Pawn.of("p", false));
        put(Position.of("f2"), Pawn.of("p", false));
        put(Position.of("g2"), Pawn.of("p", false));
        put(Position.of("h2"), Pawn.of("p", false));
    }};

    private Pawn(String name, boolean isBlack) {
        super(name, SCORE, isBlack);
    }

    public static Pawn of(String name, boolean color) {
        return new Pawn(name, color);
    }

    public static Map<Position, Pawn> initialPawnPieces() {
        return PAWNS;
    }

    @Override
    public boolean canMove2(Map<Position, Piece> board, Position start, Position end) {
        List<Direction> directions = new ArrayList<>(selectDirectionByColor());
        boolean result = movableLinear(directions.remove(0), board, start, end);
        if (!result) {
            result = movableDiagonal(directions, board, start, end);
        }
        return result;
    }

    private boolean movableDiagonal(List<Direction> directions, Map<Position, Piece> board, Position start, Position end) {
        return directions.stream()
                .map(direction -> start.move(direction))
                .filter(next -> next.equals(end) && board.containsKey(next) && !board.get(next).isSameColor(this))
                .findAny()
                .isPresent();
    }

    private boolean movableLinear(Direction direction, Map<Position, Piece> board, Position start, Position end) {
        Position next = start.move(direction);
        if (next.equals(end) && isEmptyPosition(board, end)) {
            return true;
        }
        return PAWNS.containsKey(start) && next.move(direction).equals(end) && !board.containsKey(end);
    }

    private List<Direction> selectDirectionByColor() {
        if (isBlack()) {
            return Direction.blackPawnDirection();
        }
        return Direction.whitePawnDirection();
    }
}
