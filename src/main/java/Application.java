import controller.ChessController;
import dao.DbConnection;
import dao.JdbcChessGameDao;
import service.ChessGameService;

public class Application {
    public static void main(String[] args) {
        new ChessController(new ChessGameService(new JdbcChessGameDao(new DbConnection()))).run();
    }
}
