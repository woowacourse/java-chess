import chess.controller.ChessController;
import chess.dao.InMemoryChessGameDao;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(final String[] args) {
        // DB와 연결해서 작업 시 아래 로직 사용
        // final ChessController chessController = new ChessController(new InputView(), new OutputView(), new DbChessGameDao());
        final ChessController chessController = new ChessController(new InputView(), new OutputView(), new InMemoryChessGameDao());
        chessController.run();
    }
}
