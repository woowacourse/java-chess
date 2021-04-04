package chess.controller.dto;

import chess.domain.board.Board;
import chess.domain.player.Turn;

public class GameDto {
    private Turn turn;
    private Board board;

    public Board getBoard() {
        return board;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
}
