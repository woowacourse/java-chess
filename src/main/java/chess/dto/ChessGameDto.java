package chess.dto;

import chess.domain.game.Turn;

public class ChessGameDto {

    private final int id;
    private final Turn turn;

    private ChessGameDto(final int id, final Turn turn) {
        this.id = id;
        this.turn = turn;
    }

    public static ChessGameDto of(final int id, final String turn) {
        return new ChessGameDto(id, Turn.valueOf(turn));
    }

    public int getId() {
        return id;
    }

    public Turn getTurn() {
        return turn;
    }
}
