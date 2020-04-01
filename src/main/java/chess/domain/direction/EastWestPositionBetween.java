package chess.domain.direction;

import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Row;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class EastWestPositionBetween implements BiFunction<Position, Position, List<Position>> {
    @Override
    public List<Position> apply(Position from, Position to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        Row smallerRow = Row.getSmaller(from.getRow(), to.getRow());
        Row biggerRow = Row.getBigger(from.getRow(), to.getRow());
        List<Row> rows = smallerRow.between(biggerRow);

        return rows.stream()
                .map(row -> Positions.of(row, from.getColumn()))
                .collect(Collectors.toList());
    }
}
