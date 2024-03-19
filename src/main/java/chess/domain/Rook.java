package chess.domain;

import java.util.Set;

public class Rook extends Piece {
    private static Set<Direction> directions = Direction.getFourDirection();

    public Rook(Position position, Color color) {
        super(position, color);
    }

    @Override
    public Set<Position> findMovablePositions(Position destination) {
        Direction direction = position.findDirectionTo(destination);
        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
        return position.findCourses(direction, destination);
    }

    @Override
    public Rook update(Position destination) {
        return new Rook(destination, color);
    }
}
