import controller.ChessController;
import domain.board.Board;
import domain.board.InitialChessAlignment;

public final class Application {
    public static void main(String[] args) {
        final Board board = Board.create(new InitialChessAlignment());
        final ChessController chessController = new ChessController(board);
        chessController.run();
    }
}
