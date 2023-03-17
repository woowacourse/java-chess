package chess.controller;

import chess.GameCommand;
import chess.domain.Game;
import chess.domain.board.Square;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;

    private final InputView inputView;
    private final OutputView outputView;
    private Game game;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.game = new Game();
    }

    public void run() {
        outputView.printGameStartMessage();
        boolean isEnd = false;
        while (!isEnd) {
            final GameCommand gameCommand = generateGameCommand();
            isEnd = executeGameCommand(gameCommand);
        }
    }

    private GameCommand generateGameCommand() {
        try {
            return new GameCommand(inputView.readGameCommand());
        } catch (final IllegalArgumentException e) {
            System.err.println("[ERROR] " + e.getMessage());
            return generateGameCommand();
        }
    }

    private boolean executeGameCommand(final GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            start();
            return false;
        }
        if (gameCommand.isMove()) {
            play(gameCommand);
            return false;
        }
        return true;
    }

    private void start() {
        game = new Game();
        outputView.printChessBoard(game.getPieces());
    }

    private void play(final GameCommand gameCommand) {
        try {
            final List<Square> squares = gameCommand.convertToSquare();
            final Square source = squares.get(SOURCE_INDEX);
            final Square target = squares.get(TARGET_INDEX);
            game.move(source, target);
            outputView.printChessBoard(game.getPieces());
        } catch (final IllegalArgumentException e) {
            System.err.println("[ERROR] " + e.getMessage());
        }
    }
}
