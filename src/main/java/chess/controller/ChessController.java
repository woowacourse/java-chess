package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.Command;
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
        final Board board = BoardFactory.generateBoard();
        final ChessGame chessGame = new ChessGame(board);

        outputView.printStartMessage();
        executeCommand(chessGame);

        while (chessGame.isPlayable()) {
            outputView.printBoard(chessGame.getBoard());
            executeCommand(chessGame);
        }
    }

    private void executeCommand(final ChessGame chessGame) {
        try {
            final Command command = Command.findByString(inputView.readCommand());
            command.execute(chessGame);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            executeCommand(chessGame);
        }
    }
}
