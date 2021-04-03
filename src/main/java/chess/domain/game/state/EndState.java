package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.result.EndResult;
import chess.domain.result.Result;
import chess.domain.result.StatusResult;

public final class EndState extends PlayingState {

    public EndState(final Board board) {
        super(board);
    }

    @Override
    public GameState execute(final CommandAsString command) {
        return new EndState(currentBoard());
    }

    @Override
    public Result turnResult() {
        return new EndResult(currentBoard());
    }

    @Override
    public Result statusResult() {
        return new StatusResult(currentBoard());
    }

    @Override
    public Result pathResult(Position source) {
        throw new IllegalArgumentException("게임이 종료된 후에는 경로를 확인할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public String currentState() {
        return "finished";
    }
}
