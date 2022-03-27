package chess.domain.state;

import chess.domain.Board;
import chess.domain.postion.Position;

public class Ready implements State {
    private final Board board;

    public Ready(final Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        return new White(board);
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public State changeTurn(Position source, Position target) {
        throw new IllegalArgumentException("Ready 상태에서는 chageTurn할 수 없습니다.");
    }

    @Override
    public Board board() {
        return board;
    }
}
