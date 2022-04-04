package chess.controller.dto.response;

import chess.domain.turn.Turn;

public class TurnResponse {

    private final String turn;

    private TurnResponse(String turn) {
        this.turn = turn;
    }

    public static TurnResponse from(Turn turn) {
        return new TurnResponse(turn.name());
    }
}
