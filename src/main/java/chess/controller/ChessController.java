package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public static final int COMMAND_INDEX = 0;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Board board = BoardFactory.generateBoard();
        final ChessGame chessGame = new ChessGame(board);

        while (chessGame.isRunnable()) {
            printChessBoard(chessGame);
            executeCommand(chessGame);
        }
    }

    private void printChessBoard(final ChessGame chessGame) {
        if (chessGame.isStart()) {
            outputView.printBoard(chessGame.getBoard());
        }
    }

    private void executeCommand(final ChessGame chessGame) {
        try {
            final String[] splitCommand = inputView.readCommand().split(" ");
            final Command command = Command.findByString(splitCommand[COMMAND_INDEX]);
            command.execute(chessGame, splitCommand);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            executeCommand(chessGame);
        }
    }
}
