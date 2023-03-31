package chess.dto;

import chess.domain.side.Color;

public class ChessGameDto {
    private final Long id;
    private final Color turn;

    public ChessGameDto(Long id, Color turn) {
        this.id = id;
        this.turn = turn;
    }

    public Long getId() {
        return id;
    }

    public Color getTurn() {
        return turn;
    }
}
