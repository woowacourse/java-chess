package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.board.Board;
import chess.domain.result.EndResult;
import chess.domain.result.Result;

public final class EndState extends PlayingState {

    public EndState(final Board board) {
        super(board);
    }

    @Override
    public GameState execute(final CommandAsString command) {
        throw new UnsupportedOperationException("게임이 종료한 후에는 명령을 실행할 수 없습니다.");
    }

    @Override
    public Result turnResult() {
        return new EndResult(currentBoard());
    }

    @Override
    public Result statusResult() {
        throw new IllegalArgumentException("게임이 종료된 후에는 점수를 조회할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
