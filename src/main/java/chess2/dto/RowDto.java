package chess2.dto;

import chess2.domain.board.Row;
import chess2.domain.square.Square;
import java.util.stream.Collectors;

public class RowDto {

    private final String rowDisplay;

    public RowDto(Row row) {
        this.rowDisplay = row.getSquares()
                .stream()
                .map(Square::display)
                .collect(Collectors.joining());
    }

    public String getDisplay() {
        return rowDisplay;
    }
}
