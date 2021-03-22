package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandRouter;
import chess.domain.manager.ChessGameManager;
import chess.domain.manager.ChessGameManagerFactory;
import chess.util.Repeater;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {
    public static void run() {
        OutputView.printInitialMessage();
        ChessGameManager chessGameManager = ChessGameManagerFactory.createNotStartedGameManager();
        Command command;
        do {
            command = Repeater.repeatOnError(() -> CommandRouter.findByInputCommand(InputView.getCommand()));
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
