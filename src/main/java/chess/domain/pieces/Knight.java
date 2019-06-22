package chess.domain.pieces;

import chess.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public List<Point> move(Point source, Point target) {
        Direction currentDirection = calculateDirection(source, target);
        return calculatePath(source, target, currentDirection);
    }

    @Override
    public List<Point> attack(Point source, Point target) throws IllegalArgumentException {
        return move(source, target);
    }

    private List<Point> calculatePath(Point source, Point target, Direction direction) {
        return new ArrayList<>();
    }

    private Direction calculateDirection(Point source, Point target) throws IllegalArgumentException {
        List<Direction> knightDirections = Direction.knightDirection();

        return knightDirections.stream()
                .filter(direction -> source.plusPoint(direction).equals(target))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("갈 수 없는 방향입니다."));
    }
}
