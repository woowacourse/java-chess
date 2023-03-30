import controller.ChessController;
import dao.ChessGameDao;
import dao.DbConnectionGenerator;
import service.ChessGameService;

public class Application {
    public static void main(String[] args) {
        new ChessController(new ChessGameService(new ChessGameDao(new DbConnectionGenerator()))).run();
    }
}
