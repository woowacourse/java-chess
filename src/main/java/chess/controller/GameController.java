package chess.controller;

import chess.domain.game.Game;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printGameStartMessage();
        String command = inputView.readStartCommand();

        Game game = new Game();
        while (command.equals("start")) {
            outputView.printBoard(game.getBoardStatus());
            List<String> movement = inputView.readMovement();
            game.movePiece(movement.get(0), movement.get(1));
        }
    }
}
