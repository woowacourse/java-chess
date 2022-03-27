package chess.console;

import chess.console.gamestate.GameState;
import chess.console.gamestate.Ready;
import chess.console.view.InputView;
import chess.console.view.OutputView;

public class ChessGame {

    private GameState gameState;

    public ChessGame() {
        this.gameState = new Ready();
    }

    public void run() {
        OutputView.printGameGuide();
        runGameUntilEnd();
    }

    private void runGameUntilEnd() {
        while (!gameState.isEnd()) {
            gameState = gameState.run(InputView.requestGameCommand());
        }
    }
}
