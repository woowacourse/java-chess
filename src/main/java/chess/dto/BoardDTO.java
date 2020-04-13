package chess.dto;

import java.util.Collections;
import java.util.Map;

public class BoardDTO {
    private final Map<String, String> board;

    public BoardDTO(final Map<String, String> board) {
        this.board = board;
    }

    public Map<String, String> get() {
        return Collections.unmodifiableMap(this.board);
    }
}
