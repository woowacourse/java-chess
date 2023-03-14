import domain.board.ChessBoard;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize();
        OutputView.printFuckingShit(chessBoard.getBoard());
    }
}
