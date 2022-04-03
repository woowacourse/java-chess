package chess.dto;

import chess.controller.state.Turn;

public class TurnDto {
    private final Turn turn;

    private TurnDto(Turn turn) {
        this.turn = turn;
    }

    public static TurnDto from(Turn turn) {
        return new TurnDto(turn);
    }

    public Turn getColor() {
        return turn;
    }
}