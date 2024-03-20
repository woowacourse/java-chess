import controller.ChessManager;
import view.InputView;
import view.MessageResolver;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessManager chessManager = new ChessManager(new InputView(), new OutputView(new MessageResolver()));
        chessManager.start();
    }
}
