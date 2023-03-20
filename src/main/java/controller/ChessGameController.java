package controller;

import domain.chessboard.ChessBoardFactory;
import domain.chessgame.ChessGame;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Consumer;

public final class ChessGameController {

    private GameStatus gameStatus;

    public void run() {
        OutputView.printStartMessage();
        gameStatus = new Start(new ChessGame(ChessBoardFactory.generate()));

        while (gameStatus.isKeepGaming()) {
            retryOnError(this::playGame);
        }
    }

    private void playGame(List<String> inputs) {
        gameStatus = gameStatus.setGameStatus(inputs);
        gameStatus.playTurn(inputs);

        final ChessBoardDTO chessBoardDTO = ChessBoardDTO.from(gameStatus.getChessBoard());

        OutputView.printChessBoard(chessBoardDTO);
    }

    private void retryOnError(Consumer<List<String>> playGame) {
        try {
            playGame.accept(InputView.readline());
        } catch (IllegalStateException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }

}
