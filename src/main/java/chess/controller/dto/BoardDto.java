package chess.controller.dto;

import java.util.Collections;
import java.util.Map;

public class BoardDto {
    private final Map<String, String> board;

    public BoardDto(final Map<String, String> board) {
        this.board = board;
    }

    public Map<String, String> get() {
        return Collections.unmodifiableMap(this.board);
    }
}
