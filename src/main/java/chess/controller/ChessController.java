package chess.controller;

import chess.GameCommand;
import chess.domain.Game;
import chess.domain.board.Square;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    private Game game;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameStartMessage();
        boolean isEnd = false;
        while (!isEnd) {
            final GameCommand gameCommand = readGameCommand();
            isEnd = executeCommand(gameCommand);
        }
    }

    private GameCommand readGameCommand() {
        try {
            return new GameCommand(inputView.readGameCommand());

        } catch (final IllegalArgumentException e) {
            System.err.println("[ERROR] " + e.getMessage());
            return readGameCommand();
        }
    }

    private boolean executeCommand(final GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            game = new Game();
            outputView.printChessBoard(game.getPieces());
            return false;
        }
        if (gameCommand.isMove()) {
            play(gameCommand);
            return false;
        }
        return true;
    }

    private void play(final GameCommand gameCommand) {
        try {
            final List<Square> coordinates = gameCommand.convertToCoordinate();
            game.move(coordinates.get(0), coordinates.get(1));
            outputView.printChessBoard(game.getPieces());
        } catch (final IllegalArgumentException e) {
            System.err.println("[ERROR] " + e.getMessage());
        }
    }
}
