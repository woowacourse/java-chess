package chess.controller.console;

import chess.controller.console.command.Command;
import chess.controller.console.command.CommandRouter;
import chess.domain.manager.ChessGameManager;
import chess.domain.manager.ChessGameManagerFactory;
import chess.util.Repeater;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleController {
    public static void run() {
        OutputView.printInitialMessage();
        ChessGameManager chessGameManager = ChessGameManagerFactory.createNotStartedGameManager();

        do {
            Command command = Repeater.repeatOnError(() -> CommandRouter.findByInputCommand(InputView.getCommand()));
            chessGameManager = executeCommandOrPassOnError(chessGameManager, command);
        } while (chessGameManager.isNotEnd());

        if (chessGameManager.isStart()) {
            OutputView.printResult(chessGameManager.getStatistics());
        }
    }

    private static ChessGameManager executeCommandOrPassOnError(ChessGameManager chessGameManager, Command command) {
        try {
            return command.execute(chessGameManager);
        } catch (RuntimeException e) {
            OutputView.printMessage(e.getMessage());
            return chessGameManager;
        }
    }
}
