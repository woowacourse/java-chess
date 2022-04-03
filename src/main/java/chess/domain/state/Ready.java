package chess.domain.state;

import chess.domain.Command;
import chess.domain.ChessBoard;
import chess.domain.Team;
import java.util.List;

public class Ready implements GameState {
    private final ChessBoard chessBoard;

    public Ready(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState execute(Command command, List<String> input) {
        isExecutable(command);
        if (command.isEnd()) {
            return new End();
        }
        return new WhiteTurn(ChessBoard.create());
    }

    private void isExecutable(Command command) {
        if (command.isMove() || command.isStatus()) {
            throw new IllegalArgumentException("[ERROR] start 또는 end 중에서 입력해주세요.");
        }
    }

    @Override
    public Team findWinner() {
        throw new IllegalArgumentException("[ERROR] 현재 상태에서 실행할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
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
