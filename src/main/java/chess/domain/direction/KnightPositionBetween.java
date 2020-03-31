package chess.domain.direction;

import chess.domain.position.Position;

import java.util.List;
import java.util.function.BiFunction;

public class KnightPositionBetween implements BiFunction<Position, Position, List<Position>> {
    @Override
    public List<Position> apply(Position position, Position position2) {
        throw new UnsupportedOperationException("지원되지 않는 기능입니다.");
    }
}
