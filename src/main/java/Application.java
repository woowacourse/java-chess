import chess.controller.ChessController;
import chess.dao.DBChessGameDao;

public class Application {

    public static void main(final String[] args) {
        final ChessController chessController = new ChessController(new DBChessGameDao());
        chessController.run();
    }
}
