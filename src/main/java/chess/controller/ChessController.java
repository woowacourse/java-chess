package chess.controller;

import chess.controller.state.End;
import chess.controller.state.Ready;
import chess.controller.state.State;
import chess.domain.board.Square;
import chess.domain.game.Game;
import chess.domain.game.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;

    private final InputView inputView;
    private final OutputView outputView;
    private Game game;
    private State state;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.game = new Game();
        this.state = new Ready();
    }

    public void run() {
        outputView.printGameStartMessage();

        while (!(state instanceof End)) {
            executeState();
        }
    }

    private void executeState() {
        try {
            final GameCommand gameCommand = generateGameCommand();
            executeGameCommand(gameCommand);
        } catch (final IllegalStateException e) {
            System.err.println("[ERROR] " + e.getMessage());
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

    private void executeGameCommand(final GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            state = state.start();
            game = new Game();
            outputView.printChessBoard(game.getPieces());
        }
        if (gameCommand.isMove()) {
            state = state.next();
            play(gameCommand);
        }
        if (gameCommand.isEnd()) {
            state = state.end();
        }
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
