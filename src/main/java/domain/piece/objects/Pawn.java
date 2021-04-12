package domain.piece.objects;

import domain.exception.InvalidTurnException;
import domain.exception.PieceCannotMoveException;
import domain.piece.position.Direction;
import domain.piece.position.Position;
import domain.score.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static domain.piece.Color.BLACK;
import static domain.piece.position.Direction.linearDirection;

public class Pawn extends Piece {
    private static final Score SCORE = Score.of(1);
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

    private List<Direction> selectDirectionByColor() {
        if (this.isSameColor(BLACK.getValue())) {
            return Direction.blackPawnDirection();
        }
        return Direction.whitePawnDirection();
    }

    private boolean movablePositionByDirection(Position start, Position end) {
        List<Direction> directions = new ArrayList<>(selectDirectionByColor());
        return directions.stream()
                .anyMatch(direction -> (linearDirection().contains(direction) && validLinearMove(start, end, direction))
                        || start.move(direction).equals(end));
    }

    private boolean validLinearMove(Position start, Position end, Direction direction) {
        Position movePosition = start.move(direction);
        if (movableEndPosition(movePosition, end)) {
            return true;
        }

        return movableEndPosition(movePosition.move(direction), end);
    }

    private boolean movableEndPosition(Position movePosition, Position end) {
        return movePosition.validPosition() && movePosition.equals(end);
    }

    @Override
    public void checkMovable(Position start, Position end, boolean turn) {
        if (!isSameColor(turn)) {
            throw new InvalidTurnException();
        }

        if (!movablePositionByDirection(start, end)) {
            throw new PieceCannotMoveException(name());
        }
    }

    @Override
    public Direction direction(Position start, Position end) {
        List<Direction> directions = new ArrayList<>(selectDirectionByColor());
        return directions.stream()
                .filter(direction -> start.move(direction).equals(end) ||
                        (linearDirection().contains(direction) && start.move(direction).move(direction).equals(end)))
                .findAny()
                .orElseThrow(() -> new RuntimeException("해당 위치로 가는 방향을 찾을 수 없습니다."));
    }

    @Override
    public boolean existPath() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
