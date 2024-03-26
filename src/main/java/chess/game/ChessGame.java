package chess.game;

import chess.game.status.GameStatus;
import chess.game.status.InitialGame;
import chess.view.input.InputView;
import chess.view.output.OutputView;

public class ChessGame {
    private final InputView inputView;

    public ChessGame(final InputView inputView) {
        this.inputView = inputView;
    }

    public void start() {
        OutputView.printInitialMessage();
        GameStatus gameStatus = new InitialGame(inputView);
        while (gameStatus.isPlayable()) {
            gameStatus = getGameStatus(gameStatus);
        }
    }

    private GameStatus getGameStatus(GameStatus gameStatus) {
        try {
            gameStatus = gameStatus.play();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return gameStatus;
    }
}
