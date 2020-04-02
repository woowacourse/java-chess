package chess.domain.direction;

import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class NorthSouthPositionBetween implements BiFunction<Position, Position, List<Position>> {
    @Override
    public List<Position> apply(Position from, Position to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        Column smallerColumn = Column.getSmaller(from.getColumn(), to.getColumn());
        Column biggerColumn = Column.getBigger(from.getColumn(), to.getColumn());
        List<Column> columns = smallerColumn.between(biggerColumn);

        return columns.stream()
                .map(column -> Positions.of(from.getRow(), column))
                .collect(Collectors.toList());
    }
}
