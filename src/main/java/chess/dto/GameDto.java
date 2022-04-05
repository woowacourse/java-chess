package chess.dto;

import java.util.Map;

public class GameDto {
    private final Map<String, String> board;
    private final String turn;

    public GameDto(Map<String, String> board, String turn) {
        this.board = board;
        this.turn = turn;
    }

    public Map<String, String> getBoard() {
        return board;
    }

    public String getTurn() {
        return turn;
    }
}
