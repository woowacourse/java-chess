package chess.domain.state;

import chess.domain.Command;
import chess.domain.ChessBoard;
import chess.domain.Team;
import java.util.List;

public class End implements GameState {

    private static final String END_INVALID_OPERATION_EXCEPTION = "[ERROR] 프로그램 종료 상태에서는 명령을 실행할 수 없습니다.";

    @Override
    public GameState execute(Command command, List<String> input) {
        throw new IllegalArgumentException(END_INVALID_OPERATION_EXCEPTION);
    }

    @Override
    public Team findWinner() {
        throw new IllegalArgumentException(END_INVALID_OPERATION_EXCEPTION);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public ChessBoard getChessBoard() {
        throw new IllegalArgumentException(END_INVALID_OPERATION_EXCEPTION);
    }
}
