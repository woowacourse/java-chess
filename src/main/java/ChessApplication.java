import controller.ChessGame;
import view.InputView;

public class ChessApplication {
    public static void main(String[] args) {
        final ChessGame chessGame = new ChessGame(InputView.getInstance());
        chessGame.start();
    }
}
