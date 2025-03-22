import chess.view.InputView;
import chess.view.OutputView;
import controller.ChessController;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();

        ChessController chessController = new ChessController(inputView, outputView);
        chessController.start();
    }
}
