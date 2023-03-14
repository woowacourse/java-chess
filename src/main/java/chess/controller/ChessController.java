package chess.controller;

import chess.GameCommand;
import chess.domain.Board;
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
        start();
    }

    private void start() {
        final GameCommand gameCommand = readGameCommand();
        if (gameCommand.isStart()) {
            final Board board = new Board();
            outputView.printChessBoard(board.getPieces());
        }
    }

    private GameCommand readGameCommand() {
        try {
            outputView.printGameStartMessage();
            return new GameCommand(inputView.readGameCommand());

        } catch (final IllegalArgumentException e) {
            System.err.println("[ERROR] " + e.getMessage());
            return readGameCommand();
        }
    }
}
