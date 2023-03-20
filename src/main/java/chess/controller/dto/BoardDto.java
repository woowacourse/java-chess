package chess.controller.dto;

import java.util.List;

public class BoardDto {

    private final List<List<String>> board;

    public BoardDto(final List<List<String>> board) {
        this.board = board;
    }

    public List<List<String>> getBoard() {
        return board;
    }
}
