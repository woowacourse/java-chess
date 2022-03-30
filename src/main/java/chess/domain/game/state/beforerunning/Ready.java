package chess.domain.game.state.beforerunning;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.state.State;
import chess.domain.position.Position;

public class Ready extends BeforeRunning {

    public Ready() {
        super(BoardFactory.createInitializedBoard());
    }

    @Override
    public State endGame() {
        return new FinishedKing(getBoard());
    }

    @Override
    public State move(Position from, Position to) {
        throw new IllegalStateException("게임 시작 전 말을 움직일 수 없습니다.");
    }

    @Override
    public State showStatus() {
        throw new IllegalStateException("게임 시작 전 점수를 보여줄 수 없습니다.");
    }
}
