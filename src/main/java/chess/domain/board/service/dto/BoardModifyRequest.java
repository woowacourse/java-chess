package chess.domain.board.service.dto;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public class BoardModifyRequest {

    private final Long id;
    private final Board board;
    private final Color turn;

    public BoardModifyRequest(final Long id, final Board board, final Color turn) {
        this.id = id;
        this.board = board;
        this.turn = turn;
    }

    public Long id() {
        return id;
    }

    public Board board() {
        return board;
    }

    public Color turn() {
        return turn;
    }
}
