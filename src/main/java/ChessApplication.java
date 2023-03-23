import controller.ChessController;
import view.InputView;
import view.OutputView;
import view.ScannerInputReader;

public class ChessApplication {
    public static void main(String[] args) {
        ChessController controller = new ChessController(
                new InputView(new ScannerInputReader()),
                new OutputView());
        controller.boot();
    }
}
