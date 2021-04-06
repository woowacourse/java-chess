package chess.controller.command;

import chess.domain.ChessGameManager;
import chess.exception.CommandValidationException;
import chess.view.OutputView;

import java.util.List;

public class Start implements Command {
    private static final int START_COMMAND_PROPER_SIZE = 1;

    public Start(List<String> inputCommand) {
        if (inputCommand.size() != START_COMMAND_PROPER_SIZE) {
            throw new CommandValidationException("유효하지 않은 시작 명령입니다.");
        }
    }

    @Override
    public void execute(ChessGameManager chessGameManager) {
        chessGameManager.start();
        OutputView.printBoard(chessGameManager.getBoard());
    }
}
