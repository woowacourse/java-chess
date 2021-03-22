package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandRouter;
import chess.domain.ChessGameManager;
import chess.util.Repeater;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {
    public static void run() {
        OutputView.printInitialMessage();
        ChessGameManager chessGameManager = new ChessGameManager();
        Command command;
        do {
            command = Repeater.repeatOnError(() -> CommandRouter.findByInputCommand(InputView.getCommand()));
            executeCommandOrPassOnError(chessGameManager, command);
        } while (!chessGameManager.isEnd());

        if (chessGameManager.hasGame()) {
            printGameEndMessageIfKingDeadCondition(chessGameManager);
            OutputView.printResult(chessGameManager.getStatistics());
        }
        OutputView.printTerminationMessage();
    }

    private static void printGameEndMessageIfKingDeadCondition(ChessGameManager chessGameManager) {
        if (chessGameManager.isKingDeadEndCondition()) {
            OutputView.printKingDeadMessage();
        }
    }

    private static void executeCommandOrPassOnError(ChessGameManager chessGameManager, Command command) {
        try {
            command.execute(chessGameManager);
        } catch (RuntimeException e) {
            OutputView.printMessage(e.getMessage());
        }
    }
}
