package chess.controller;

import chess.view.InputView;
import chess.view.OutputView;

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
            System.out.println(e.getMessage());
        }
        return gameStatus;
    }
}
