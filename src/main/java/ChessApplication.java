import controller.ChessController;
import view.OutputView;

public class ChessApplication {
    public static void main(String[] args) {
        ChessController controller = new ChessController(new OutputView());
        controller.printBoardStatus();
    }
}
