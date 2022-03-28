package chess.domain.game.state.beforerunning;

import chess.domain.board.Board;
import chess.domain.game.state.State;
import chess.domain.position.Position;

public class FinishedEnd extends BeforeRunning {

    public FinishedEnd(Board board) {
        super(board);
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
    public State showStatus() {
        throw new IllegalStateException("게임이 끝나서 상태를 볼 수 없습니다.");
    }
}
