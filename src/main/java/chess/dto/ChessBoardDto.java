package chess.dto;

import java.util.List;

public class ChessBoardDto {
    private List<String> rows;
    private boolean isGameOver;

    public ChessBoardDto(List<String> rows, boolean isGameOver) {
        this.rows = rows;
        this.isGameOver = isGameOver;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public List<String> getRows() {
        return rows;
    }
}
