package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.board.Board;
import chess.domain.game.Result;

public final class EndState extends PlayingState {

    public EndState(final Board board) {
        super(board);
    }

    @Override
    public GameState execute(final CommandAsString command) {
        throw new UnsupportedOperationException("게임이 종료한 후에는 명령을 실행할 수 없습니다.");
    }

    @Override
    public Result statusResult() {
        // end game needs to say something
        return null;
    }

    @Override
    public Result scoreResult() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
