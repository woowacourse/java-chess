package chess.dto;

import java.util.List;
import java.util.Map;

public class BoardDto {
    private Map<String, List<String>> board;

    public BoardDto(Map<String, List<String>> board) {
        this.board = board;
    }

    public Map<String, List<String>> getBoard() {
        return board;
    }

    public void setBoard(Map<String, List<String>> board) {
        this.board = board;
    }
}
