import chess.controller.ChessController;
import chess.view.InputView;

public class Application {
    public static void main(String[] args) {
        ChessController controller = new ChessController(InputView.getInstance());
        controller.run();
    }
}
