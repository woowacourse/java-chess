package chess.controller;

import chess.domain.Command;
import chess.domain.Game;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        try {
            executeGame();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
        }
    }

    private void executeGame() {
        OutputView.printGuideMessage();
        if (Command.isStart(InputView.receiveInput())) {
            playGame();
        }
    }

    private void playGame() {
        Game game = new Game();
        OutputView.printBoard(game.getBoard());
        while(game.isPlaying()) {
            game.command(InputView.receiveInput());
        }
    }
}
