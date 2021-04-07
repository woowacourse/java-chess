package chess.controller.dto;

import chess.domain.board.Board;
import chess.domain.player.Turn;

public class GameDto {
    private final Turn turn;
    private final Board board;

    public GameDto(final Turn turn, final Board board) {
        this.board = board;
        this.turn = turn;
    }

    public Board getBoard() {
        return board;
    }

    public Turn getTurn() {
        return turn;
    }
}
