package chess.dao.ChessGame.dto;

import chess.domain.game.Turn;

public class FindResponseDto {

    private final Long id;
    private final Turn turn;

    public FindResponseDto(final Long id, final Turn turn) {
        this.id = id;
        this.turn = turn;
    }

    public Long getId() {
        return id;
    }
}
