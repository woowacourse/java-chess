package chess;

import chess.controller.ChessController;
import chess.controller.GameStatus;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Scanner;

public final class MainApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final GameStatus gameStatus = GameStatus.INIT;
            final ChessController chessController = new ChessController(new InputView(scanner), new OutputView());
            run(gameStatus, chessController);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void run(GameStatus gameStatus, final ChessController chessController) {
        while (gameStatus.playable()) {
            gameStatus = chessController.run();
        }
    }
}
