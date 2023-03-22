package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
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

        outputView.printStartMessage();
        while (chessGame.isRunnable()) {
            printChessBoard(chessGame);
            executeCommand(chessGame);
        }
        outputView.printResult(chessGame.calculateScore());
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
