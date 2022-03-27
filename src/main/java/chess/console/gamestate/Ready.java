package chess.console.gamestate;

import chess.console.view.OutputView;
import chess.domain.ChessBoard;

public final class Ready implements GameState {

    @Override
    public GameState run(String requestCommand) {
        Command command = Command.toCommand(requestCommand);
        if (!command.isStart()) {
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
