package chess.web.dto;

import chess.domain.Color;
import chess.domain.board.Board;

public class GameStateDto {

    private String end;
    private String turn;

    private GameStateDto(boolean end, Color turn) {
        this.end = String.valueOf(end);
        this.turn = turn.name().toLowerCase();
    }

    public static GameStateDto from(Board board) {
        return new GameStateDto(board.isEnd(), board.getTurn());
    }

    public String getEnd() {
        return end;
    }

    public String getTurn() {
        return turn;
    }
}
