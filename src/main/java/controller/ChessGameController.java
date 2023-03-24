package controller;

import dao.ChessDao;
import domain.chessboard.ChessBoardFactory;
import domain.chessgame.ChessGame;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.BiFunction;

public final class ChessGameController {

    public void run() {
        OutputView.printStartMessage();
        playGame();
    }

    private void playGame() {
        GameStatus gameStatus = new LoadGame(new ChessGame(ChessBoardFactory.generate()), new ChessDao());

        while (gameStatus.isKeepGaming()) {
            gameStatus = retryOnError(this::playTurn, gameStatus);
        }
    }

    private GameStatus playTurn(final List<String> input, final GameStatus gameStatus) {
        final GameStatus transition = gameStatus.transition(input);
        transition.playTurn(input);
        return transition;
    }

    private GameStatus retryOnError(final BiFunction<List<String>, GameStatus, GameStatus> playGame, final GameStatus gameStatus) {
        while (true) {
            try {
                return playGame.apply(InputView.readline(), gameStatus);
            } catch (IllegalStateException exception) {
                OutputView.printErrorMessage(exception.getMessage());
            }
        }
    }

}
