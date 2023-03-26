package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandFactory;
import chess.dao.ChessDao;
import chess.domain.ChessBoardFactory;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessGame chessGame = loadChessGame();

        outputView.printGameGuide();
        while (chessGame.isNotEnd()) {
            executeCommand(chessGame);
        }
        outputView.printWinningTeam(chessGame.findWinningTeam());
    }

    private ChessGame loadChessGame() {
        ChessDao chessDao = new ChessDao();
        ChessGame chessGame = chessDao.load();
        if (chessGame == null) {
            chessGame = new ChessGame(ChessBoardFactory.create());
        }
        return chessGame;
    }

    private void executeCommand(final ChessGame chessGame) {
        try {
            Command command = CommandFactory.from(inputView.readCommandAndParameters());
            command.execute(chessGame, outputView);
        } catch (IllegalArgumentException | UnsupportedOperationException | IllegalStateException e) {
            outputView.printError(e.getMessage());
            executeCommand(chessGame);
        }
    }
}
