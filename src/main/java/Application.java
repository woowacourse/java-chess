import chess.controller.ChessController;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessController controller = new ChessController(InputView.getInstance(), OutputView.getInstance());
        controller.run();
    }
}
