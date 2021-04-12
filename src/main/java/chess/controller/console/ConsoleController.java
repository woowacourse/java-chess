package chess.controller.console;

import chess.controller.console.command.Command;
import chess.controller.console.command.CommandRouter;
import chess.domain.manager.ChessGameManager;
import chess.domain.manager.ChessGameManagerFactory;
import chess.service.ChessService;
import chess.util.Repeater;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleController {
    private static final int TEMPORARY_GAME_ID = 0;

    private final ChessService chessService;

    public ConsoleController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        OutputView.printInitialMessage();
        ChessGameManager chessGameManager = ChessGameManagerFactory.createNotStartedGameManager(TEMPORARY_GAME_ID);
        do {
            Command command = Repeater.repeatOnError(() -> CommandRouter.findByInputCommand(InputView.getCommand()));
            chessGameManager = executeCommandOrPassOnError(chessGameManager.getId(), chessService, command);
        } while (chessGameManager.isNotEnd());

        if (chessGameManager.isStart()) {
            OutputView.printResult(chessGameManager.getStatistics());
        }
    }

    private ChessGameManager executeCommandOrPassOnError(long gameId, ChessService chessService, Command command) {
        try {
            return command.execute(chessService, gameId);
        } catch (RuntimeException e) {
            OutputView.printMessage(e.getMessage());
            return chessService.findById(gameId);
        }
    }
}
