package chess.domain.piece;

import chess.domain.board.Column;
import chess.domain.board.Row;

import java.util.function.Function;

/*
 *  N(North) - 북쪽으로 한 칸 이동(Row 1 증가)
 *  S(South) - 남쪽으로 한 칸 이동(Row 1 감소)
 *  E(East) - 동쪽으로 한 칸 이동(Column 1 증가)
 *  W(West) - 서쪽으로 한 칸 이동(Column 1 감소)
 */

public enum Direction {

    N(Function.identity(), Row::next),
    NE(Column::next, Row::next),
    E(Column::next, Function.identity()),
    SE(Column::next, Row::previous),
    S(Function.identity(), Row::previous),
    SW(Column::previous, Row::previous),
    W(Column::previous, Function.identity()),
    NW(Column::previous, Row::next),
    NNE(Column::next, row -> row.jump(2)),
    NEE(column -> column.jump(2), Row::next),
    SEE(column -> column.jump(2), Row::previous),
    SSE(Column::next, row -> row.jump(-2)),
    SSW(Column::previous, row -> row.jump(-2)),
    SWW(column -> column.jump(-2), Row::previous),
    NWW(column -> column.jump(-2), Row::next),
    NNW(Column::previous, row -> row.jump(2));

    private final Function<Column, Column> columnDestination;
    private final Function<Row, Row> rowDestination;

    Direction(Function<Column, Column> columnDestination, Function<Row, Row> rowDestination) {
        this.columnDestination = columnDestination;
        this.rowDestination = rowDestination;
    }

    public Column findColumnDestination(Column column) {
        return columnDestination.apply(column);
    }

    public Row findRowDestination(Row row) {
        return rowDestination.apply(row);
    }
}
