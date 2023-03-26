import controller.ChessController;
import domain.ChessGame;
import domain.board.ChessBoard;

public final class Application {

    public static void main(String[] args) {
        final ChessController chessController = new ChessController(new ChessGame(new ChessBoard()));
        chessController.ready();
        chessController.play();
    }
}
