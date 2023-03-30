package chess.dao.ChessGame.dto;

import chess.domain.game.Turn;

public class SaveRequestDto {
    private final Turn turn;

    public SaveRequestDto(final Turn turn) {
        this.turn = turn;
    }

    public Turn getTurn() {
        return turn;
    }
}
