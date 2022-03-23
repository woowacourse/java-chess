package chess.controller;

import chess.controller.api.Request;
import chess.domain.game.Game;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputVIew) {
        this.inputView = inputView;
        this.outputView = outputVIew;
    }

    public void run() {
        Game game = new Game();
        outputView.printIntroduction();

        game.execute(Request.of(inputView.inputCommand()));
        while (game.isRunnable()) {
            outputView.printBoard(game.getPointPieces());
            game.execute(Request.of(inputView.inputCommand()));
        }
    }
}
