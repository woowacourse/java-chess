package chess.dto;

import chess.domain.piece.Color;

public class ResultDto {

    private final Color winner;

    public ResultDto(final Color winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner.name();
    }
}
