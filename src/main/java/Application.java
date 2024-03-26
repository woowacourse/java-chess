import controller.ChessController;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessController chessController = new ChessController(new InputView(), new OutputView());
        chessController.run();
    }
}
