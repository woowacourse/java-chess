package application;

import controller.ChessController;
import controller.InputController;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final InputView inputView = new InputView(new Scanner(System.in));
        final OutputView outputView = new OutputView();
        final InputController inputController = new InputController(inputView, outputView);

        final ChessController chessController = new ChessController(inputController, outputView);
        chessController.run();
    }
}
