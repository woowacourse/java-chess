package chess.controller.console.command;

import chess.domain.manager.ChessGameManager;
import chess.view.OutputView;

import java.util.List;

public class Start implements Command {
    private static final int START_COMMAND_PROPER_SIZE = 1;

    public Start(List<String> inputCommand) {
        if (inputCommand.size() != START_COMMAND_PROPER_SIZE) {
            throw new IllegalArgumentException("유효하지 않은 시작 명령입니다.");
        }
    }

    @Override
    public ChessGameManager execute(ChessGameManager chessGameManager) {
        chessGameManager = chessGameManager.start();
        OutputView.printBoard(chessGameManager.getBoard());
        return chessGameManager;
    }
}
