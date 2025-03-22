import chess.board.ChessBoard;
import chess.contoller.ChessGameManager;

public class ChessApplication {

    public static void main(String[] args) {
        ChessGameManager manager = new ChessGameManager(new ChessBoard());
        manager.run();
    }
}
