package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.piece.Color;

import java.util.List;

public abstract class AfterStart implements State {
    private final Board board;

    public AfterStart(Board board) {
        this.board = board;
    }

    protected Board board() {
        return this.board;
    }
}
