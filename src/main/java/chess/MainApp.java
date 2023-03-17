package chess;

import java.util.Scanner;

import chess.view.InputView;
import chess.view.OutputView;
import chess.controller.ChessController;

public final class MainApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final ChessController chessController = new ChessController(new InputView(scanner), new OutputView());
            chessController.run();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
