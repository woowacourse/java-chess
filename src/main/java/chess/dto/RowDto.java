package chess.dto;

import chess.domain.board.Row;
import chess.domain.square.Square;
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
