import controller.ChessGameController;
import domain.pieces.Pieces;
import domain.pieces.PiecesFactory;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView.printStart();
        Pieces pieces = Pieces.of(PiecesFactory.create());
        ChessGameController chessGameController = new ChessGameController(pieces);
        chessGameController.gameChess();
    }
}
