package chess.controller.command;

import chess.domain.ChessGameManager;
import chess.domain.statistics.ChessGameStatistics;
import chess.exception.CommandValidationException;
import chess.view.OutputView;

import java.util.List;

public class Status implements Command {
    private static final int STATUS_COMMAND_PROPER_SIZE = 1;

    public Status(List<String> inputCommand) {
        if (inputCommand.size() != STATUS_COMMAND_PROPER_SIZE) {
            throw new CommandValidationException("유효하지 않은 상태 출력 명령입니다.");
        }
    }

    @Override
    public void execute(ChessGameManager chessGameManager) {
        ChessGameStatistics chessGameStatistics = chessGameManager.getStatistics();
        OutputView.printResult(chessGameStatistics);
    }
}
