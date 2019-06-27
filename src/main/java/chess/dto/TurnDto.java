package chess.dto;

import chess.domain.Turn;

public class TurnDto {
    private Turn turn;

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
}
