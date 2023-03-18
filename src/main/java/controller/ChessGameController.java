package controller;

import domain.chessboard.ChessBoard;
import domain.chessgame.ChessGame;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Consumer;

public class ChessGameController {

    private GameStatus gameStatus;

    public void run() {
        OutputView.printStartMessage();
        gameStatus = new Start(new ChessGame(ChessBoard.generate()));

        while (gameStatus.isKeepGaming()) {
            retryOnError(this::playGame);
        }
    }

    private void playGame(List<String> inputs) {
        gameStatus = gameStatus.setGameStatus(inputs);
        gameStatus.playTurn(inputs);

        OutputView.printChessBoard(gameStatus.getChessBoard());
    }

    private void retryOnError(Consumer<List<String>> playGame) {
        try {
            playGame.accept(InputView.readline());
        } catch (IllegalStateException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }

}
