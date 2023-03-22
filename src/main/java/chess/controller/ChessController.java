package chess.controller;

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

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.game = new Game();
    }

    public void run() {
        this.outputView.printGameStartMessage();
        boolean isEnd = false;
        while (!isEnd) {
            final GameCommand gameCommand = this.generateGameCommand();
            isEnd = this.executeGameCommand(gameCommand);
        }
    }

    private GameCommand generateGameCommand() {
        try {
            return new GameCommand(this.inputView.readGameCommand());
        } catch (final IllegalArgumentException e) {
            this.outputView.printErrorMessage(e.getMessage());
            return this.generateGameCommand();
        }
    }

    private boolean executeGameCommand(final GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            this.start();
            return false;
        }
        if (gameCommand.isMove()) {
            this.play(gameCommand);
            return false;
        }
        return true;
    }

    private void start() {
        this.game = new Game();
        this.outputView.printChessBoard(this.game.getPieces());
    }

    private void play(final GameCommand gameCommand) {
        try {
            final List<Square> squares = gameCommand.convertToSquare();
            final Square source = squares.get(SOURCE_INDEX);
            final Square target = squares.get(TARGET_INDEX);
            this.game.move(source, target);
            this.outputView.printChessBoard(this.game.getPieces());
        } catch (final IllegalArgumentException e) {
            this.outputView.printErrorMessage(e.getMessage());
        }
    }
}
