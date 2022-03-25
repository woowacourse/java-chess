package chess.gamestate;

import chess.domain.ChessBoard;
import chess.view.OutputView;

public class Ready implements GameState {

    @Override
    public GameState run(String command) {
        Command cmd = Command.toCommand(command);
        if (!cmd.isStart()) {
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
