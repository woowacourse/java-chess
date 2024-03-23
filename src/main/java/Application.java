import controller.ChessController;
import view.InputView;
import view.MessageResolver;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessController chessController = new ChessController(new InputView(), new OutputView(new MessageResolver()));
        chessController.start();
    }
}
