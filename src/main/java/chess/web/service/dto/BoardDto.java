package chess.web.service.dto;

import java.util.Map;

public class BoardDto {

    private final String turn;
    private final Map<String, String> board;
    private final boolean isFinish;

    public BoardDto(String turn, Map<String, String> board, boolean isFinish) {
        this.turn = turn;
        this.board = board;
        this.isFinish = isFinish;
    }

    public Map<String, String> getBoard() {
        return board;
    }

    public String getTurn() {
        return turn;
    }

    public boolean isFinish() {
        return isFinish;
    }

}
