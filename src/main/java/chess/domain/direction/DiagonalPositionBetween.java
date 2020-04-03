package chess.domain.direction;

import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

public class DiagonalPositionBetween implements BiFunction<Position, Position, List<Position>> {
    @Override
    public List<Position> apply(Position from, Position to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        Column smallerColumn = Column.getSmaller(from.getColumn(), to.getColumn());
        Column biggerColumn = Column.getBigger(from.getColumn(), to.getColumn());
        Row smallerRow = Row.getSmaller(from.getRow(), to.getRow());
        Row biggerRow = Row.getBigger(from.getRow(), to.getRow());

        List<Row> rows = smallerRow.between(biggerRow);
        List<Column> columns = smallerColumn.between(biggerColumn);
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            positions.add(Positions.of(rows.get(i), columns.get(i)));
        }
        return positions;
    }
}
