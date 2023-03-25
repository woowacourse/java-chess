package chess;

import chess.controller.ChessGameController;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();

        new ChessGameController(inputView, outputView).start();

        scanner.close();
    }
}
