package chess.console.game;

import chess.console.game.playstate.PlayState;
import chess.console.game.playstate.Ready;
import chess.console.view.InputView;
import chess.console.view.OutputView;

public class ChessGame {

    private PlayState playState;

    public ChessGame() {
        this.playState = new Ready();
    }

    public void run() {
        OutputView.printGameGuide();
        runGameUntilEnd();
    }

    private void runGameUntilEnd() {
        while (!playState.isEnd()) {
            playState = playState.run(InputView.requestGameCommand());
        }
    }
}
