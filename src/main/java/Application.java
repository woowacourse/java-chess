import controller.ChessController;
import dao.ChessGameDao;
import dao.DbConnectionGenerator;
import service.ChessGameService;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        new ChessController(new InputView(), new OutputView(), new ChessGameService(new ChessGameDao(new DbConnectionGenerator()))).run();
    }
}
