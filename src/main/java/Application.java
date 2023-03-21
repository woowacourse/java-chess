import controller.ChessController;
import domain.ChessGame;
import view.InputView;
import view.OutputView;

public final class Application {

    public static void main(String[] args) {
        final ChessController controller = new ChessController(new ChessGame(), new InputView(), new OutputView());
        controller.play();
    }
}
