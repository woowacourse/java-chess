package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;

public abstract class Finished extends Started {
    public Finished(Board board) {
        super(board);
    }

    @Override
    public State start() {
        return new Init(board());
    }

    @Override
    public void moveIfValidColor(Position source, Position target) {
        throw new IllegalStateException("게임이 이미 종료되었습니다.");
    }

    @Override
    public State end() {
        return new End(board());
    }

    @Override
    public boolean isNotFinished() {
        return false;
    }
}
