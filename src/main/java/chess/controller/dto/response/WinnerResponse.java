package chess.controller.dto.response;

import chess.domain.Color;

public class WinnerResponse {

    private final String winner;

    private WinnerResponse(String winner) {
        this.winner = winner;
    }

    public static WinnerResponse from(Color color) {
        return new WinnerResponse(color.name());
    }

    public String getWinner() {
        return winner;
    }
}
