package chess.dto;

import java.util.List;

public class BoardDto {

    private final List<List<String>> board;

    public BoardDto(List<List<String>> board) {
        this.board = board;
    }

    public List<List<String>> board() {
        return board;
    }
}
