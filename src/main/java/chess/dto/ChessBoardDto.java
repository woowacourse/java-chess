package chess.dto;

import java.util.List;

public class ChessBoardDto {
    private List<String> rows;

    public ChessBoardDto(List<String> rows) {
        this.rows = rows;
    }

    public List<String> getRows() {
        return rows;
    }
}
