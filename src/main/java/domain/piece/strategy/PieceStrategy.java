package domain.piece.strategy;

import domain.direction.Direction;
import java.util.Set;

public class PieceStrategy implements Strategy {

    private final Set<Direction> directions;

    public PieceStrategy(Set<Direction> directions) {
        this.directions = directions;
    }

    @Override
    public Direction findDirection(int rowDifference, int columnDifference) {
        return directions.stream()
                .filter(direction -> direction.isEqualDirection(rowDifference, columnDifference))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하는 움직임 전략이 없습니다."));
    }
}
