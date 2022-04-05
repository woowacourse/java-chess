package chess.domain.state;

import chess.domain.Command;
import chess.domain.ChessBoard;
import chess.domain.Team;
import java.util.List;

public class Finish implements GameState{
    private static final String FINISH_INVALID_OPERATION_EXCEPTION = "[ERROR] 게임 종료 상태에서는 명령을 실행할 수 없습니다.";

    private final ChessBoard chessBoard;

    public Finish(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState execute(Command command, List<String> input) {
        throw new IllegalArgumentException(FINISH_INVALID_OPERATION_EXCEPTION);
    }

    @Override
    public Team findWinner() {
        return chessBoard.judgeWinner();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
