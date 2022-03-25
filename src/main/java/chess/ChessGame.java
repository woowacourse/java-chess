package chess;

import chess.gamestate.GameState;
import chess.gamestate.Ready;
import chess.view.InputView;
import chess.view.OutputView;

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
