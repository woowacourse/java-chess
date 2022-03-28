package chess.console.command;

import chess.console.view.OutputView;
import chess.domain.ChessBoard;
import java.util.Objects;

public class Ready implements Command {

    @Override
    public Command run(String command) {
        Objects.requireNonNull(command, "command는 null이 들어올 수 없습니다.");
        if (!command.equals("start")) {
            throw new IllegalArgumentException("게임이 시작되지 않아 다른 명령을 실행할 수 없습니다.");
        }
        ChessBoard chessBoard = ChessBoard.createNewChessBoard();
        OutputView.printChessBoard(chessBoard.getPieces());
        return Running.createFirstTurnRunning(chessBoard);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
