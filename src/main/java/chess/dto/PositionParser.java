package chess.dto;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.view.ColumnMapper;
import chess.view.RowMapper;

public class PositionParser {

    private static final int INPUT_COLUMN_INDEX = 0;
    private static final int INPUT_ROW_INDEX = 1;

    public static Position parsing(String positionValue) {
        Row row = RowMapper.findByInputValue(positionValue.split("")[INPUT_ROW_INDEX]);
        Column column = ColumnMapper.findByInputValue(positionValue.split("")[INPUT_COLUMN_INDEX]);
        return new Position(row, column);
    }
}
