package chess.domain.board.service.dto;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public class BoardRegisterRequest {

    private final Board board;
    private final Color turn;

    public BoardRegisterRequest(final Board board, final Color turn) {
        this.board = board;
        this.turn = turn;
    }

    public Board board() {
        return board;
    }

    public Color turn() {
        return turn;
    }
}
