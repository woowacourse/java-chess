import controller.ChessController;
import domain.service.DBService;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        DBService dbService = new DBService();
        ChessController chessController = new ChessController(inputView, outputView, dbService);

        chessController.run();
    }
}
