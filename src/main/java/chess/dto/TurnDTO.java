package chess.dto;

import chess.domain.feature.Color;

public class TurnDTO {
    private final String turn;

    public TurnDTO(Color turn) {
        this.turn = turn.getColor();
    }

    public String getTurn() {
        return turn;
    }
}
