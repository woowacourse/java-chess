import chess.ChessGame;
import chess.board.ChessBoard;
import view.ConsoleView;
import view.support.OutputSupporter;

public class Application {

    public static void main(String[] args) {
        ChessGame game = new ChessGame(ChessBoard.initialize(), new ConsoleView(new OutputSupporter()));
        game.start();
    }
}
