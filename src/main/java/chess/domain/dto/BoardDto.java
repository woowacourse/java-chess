package chess.domain.dto;

import java.util.List;

public class BoardDto {
    private List<List<String>> board;

    public BoardDto(List<List<String>> board) {
        this.board = board;
    }

    public List<List<String>> getBoard() {
        return board;
    }
}
