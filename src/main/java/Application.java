import common.TransactionContext;
import controller.ChessController;
import domain.ChessGame;
import domain.dao.ChessInformationDaoImpl;
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
        return new ChessGame(new ChessInformationDaoImpl(), new TransactionContext());
    }
}
