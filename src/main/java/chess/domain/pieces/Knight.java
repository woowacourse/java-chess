package chess.domain.pieces;

import chess.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(Type.KNIGHT, color);
    }

    @Override
    public List<Point> action(Point source, Point target, boolean hasEnemy) {
        List<Direction> knightDirections = Direction.knightDirection();

        knightDirections.stream()
                .filter(direction -> source.plusPoint(direction).equals(target))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("갈 수 없는 방향입니다."));
        return calculatePath();
    }

    private List<Point> calculatePath() {
        return new ArrayList<>();
    }
}
