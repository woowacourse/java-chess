package chess.domain.game.state.beforerunning;

import chess.domain.board.BoardFactory;
import chess.domain.game.state.State;
import chess.domain.game.state.afterrunning.FinishedEnd;
import chess.domain.game.state.afterrunning.FinishedKing;
import chess.domain.position.Position;

public class Ready extends BeforeRunning {

    public Ready() {
        super(BoardFactory.createInitializedBoard());
    }

    @Override
    public State endGame() {
        return new FinishedEnd(board);
    }

    @Override
    public State move(Position from, Position to) {
        throw new IllegalStateException("게임 시작 전 말을 움직일 수 없습니다.");
    }
}
