package chess.domain;

import java.util.Set;

public class King extends Piece {
    private static Set<Direction> directions = Direction.getEightDirection();

    public King(Position position, Color color) {
        super(position, color);
    }

    @Override
    public Set<Position> findMovablePositions(Position destination) {
        Set<Position> movable = position.findMovablePositions(directions);

        if (movable.contains(destination)) {
            return Set.of(destination);
        }
        throw new IllegalArgumentException("이동할 수 없습니다.");
    }

    @Override
    public King update(Position destination) {
        return new King(destination, color);
    }
}
