package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandRouter;
import chess.domain.ChessGameManager;
import chess.domain.GameStatus;
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
        } while(!command.isEnd() && !chessGameManager.isSameStatus(GameStatus.END));

        if (chessGameManager.isSameStatus(GameStatus.END)) {
            OutputView.printResult(chessGameManager.getStatistics());
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
