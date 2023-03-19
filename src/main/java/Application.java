import controller.ChessController;
import view.InputView;
import view.OutputView;

import java.util.Scanner;

public final class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();
        ChessController chessController = new ChessController(inputView, outputView);
        chessController.run();
    }
}
