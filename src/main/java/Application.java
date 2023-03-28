import common.JdbcContext;
import common.JdbcContextImpl;
import common.connection.JdbcConnection;
import controller.ChessController;
import domain.ChessGame;
import domain.dao.BoardDaoImpl;
import domain.dao.PieceDaoImpl;
import domain.repository.ChessRepository;
import domain.repository.ChessRepositoryImpl;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final ChessController controller = makeController();
        controller.play();
    }

    private static ChessController makeController() {
        return new ChessController(makeChessGame(), new InputView(), new OutputView());
    }

    private static ChessGame makeChessGame() {
        return new ChessGame(chessRepository());
    }

    private static ChessRepository chessRepository() {
        final JdbcContext jdbcContext = makeJdbcContext();
        return new ChessRepositoryImpl(new BoardDaoImpl(jdbcContext), new PieceDaoImpl(jdbcContext));
    }

    private static JdbcContext makeJdbcContext() {
        return new JdbcContextImpl(makeJdbcConnection());
    }

    private static JdbcConnection makeJdbcConnection() {
        return new JdbcConnection();
    }
}
