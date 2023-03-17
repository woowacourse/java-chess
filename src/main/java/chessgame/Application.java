package chessgame;

import chessgame.controller.ChessController;
import chessgame.view.InputView;
import chessgame.view.OutputView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();
        ChessController chessController = new ChessController(inputView, outputView);
        chessController.run();
    }
}
