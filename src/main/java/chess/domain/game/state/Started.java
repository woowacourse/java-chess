package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

public abstract class Started implements State {
    private final Board board;

    public Started(Board board) {
        this.board = board;
    }

    protected Board board() {
        return this.board;
    }

    @Override
    public State passTurn() {
        throw new IllegalStateException("현재 수행할 수 없는 명령입니다.");
    }

    @Override
    public List<Map<Position, Piece>> squares() {
        return board.squares();
    }

    @Override
    public boolean isNotFinished() {
        return true;
    }
}
