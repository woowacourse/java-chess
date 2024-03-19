import controller.ChessGame;
import view.InputView;
import view.OutputView;

public class ChessApplication {
    public static void main(String[] args) {
        final ChessGame chessGame = new ChessGame(InputView.getInstance(), OutputView.getInstance());
        chessGame.start();
    }
}
