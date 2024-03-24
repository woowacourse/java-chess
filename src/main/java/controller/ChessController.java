package controller;

import game.ChessGame;
import game.command.ChessCommand;
import view.InputView;
import view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        final ChessGame chessGame = new ChessGame();

        outputView.printStartHeader();

        play(chessGame);
    }

    private void play(final ChessGame chessGame) {
        while (true) {
            final ChessCommand chessCommand = inputView.readCommand();
            try {
                chessCommand.execute(chessGame);
                if (chessGame.isEnd()) {
                    break;
                }
                outputView.printChessTable(chessGame.getPieceSquares());
            } catch (final IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
