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

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        game = new Game();
    }

    public void run() {
        outputView.printGameStartMessage();
        boolean isEnd = false;
        while (!isEnd) {
            GameCommand gameCommand = generateGameCommand();
            isEnd = executeGameCommand(gameCommand);
        }
    }

    private GameCommand generateGameCommand() {
        try {
            return new GameCommand(inputView.readGameCommand());
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return generateGameCommand();
        }
    }

    private boolean executeGameCommand(GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            start();
            return false;
        }
        if (gameCommand.isMove()) {
            play(gameCommand);
            return game.isGameEnd();
        }
        return true;
    }

    private void start() {
        game = new Game();
        outputView.printChessBoard(game.getPieces());
    }

    private void play(GameCommand gameCommand) {
        try {
            List<Square> squares = gameCommand.convertToSquare();
            Square source = squares.get(SOURCE_INDEX);
            Square target = squares.get(TARGET_INDEX);
            game.move(source, target);
            outputView.printChessBoard(game.getPieces());
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }
}
