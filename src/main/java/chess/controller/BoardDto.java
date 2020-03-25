package chess.controller;

import java.util.Map;

public class BoardDto {

    private Map<String, String> board;

    public BoardDto(Map<String, String> board) {
        this.board = board;
    }

    public Map<String, String> get() {
        return board;
    }
}
