package chess.domain.piece;

import chess.domain.Point;

import java.util.Optional;

public class Empty extends Piece {
    public Empty(String name, String color, Point point) {
        super(name, color, point);
    }

    @Override
    public Optional<Direction> direction(Piece target) {
        throw new IllegalArgumentException("비어 있는 칸입니다.");
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }
}
