import controller.ChessGameController;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);

        OutputView outputView = new OutputView();

        ChessGameController chessGameController = new ChessGameController(inputView, outputView);
        chessGameController.run();
    }
}
