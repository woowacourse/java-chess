package chess.dto;

import java.util.Map;

public class BoardDto {
    private Map<String, String> board;

    public Map<String, String> getBoard() {
        return board;
    }

    public void setBoard(final Map<String, String> board) {
        this.board = board;
    }
}
