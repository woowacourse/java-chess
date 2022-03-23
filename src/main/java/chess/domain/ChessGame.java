package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class ChessGame {

    private final Board board;

    public ChessGame(final Board board) {
        this.board = board;
    }

    public void move(final String rawSource, final String rawTarget) {
        Position source = Position.valueOf(rawSource);
        Position target = Position.valueOf(rawTarget);
    }
}
