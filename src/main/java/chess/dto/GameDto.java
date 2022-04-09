package chess.dto;

import java.util.List;
import java.util.Map;

public class GameDto {
    private Map<String, List<String>> board;
    private String turn;

    public GameDto(Map<String, List<String>> board, String turn) {
        this.board = board;
        this.turn = turn;
    }

    public Map<String, List<String>> getBoard() {
        return board;
    }

    public String getTurn() {
        return turn;
    }
}
