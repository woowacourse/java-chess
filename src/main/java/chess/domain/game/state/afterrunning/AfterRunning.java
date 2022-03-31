package chess.domain.game.state.afterrunning;

import chess.domain.board.Board;
import chess.domain.game.state.Started;
import chess.domain.game.state.State;
import chess.domain.position.Position;

public abstract class AfterRunning extends Started {

    public AfterRunning(Board board) {
        super(board);
    }

    @Override
    public State startGame() {
        throw new IllegalStateException("이미 게임 중 입니다.");
    }

    @Override
    public State endGame() {
        return this;
    }

    @Override
    public State move(Position from, Position to) {
        throw new IllegalStateException("게임 중이 아니어서 말을 움직일 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
