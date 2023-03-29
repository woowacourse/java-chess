import controller.ChessGameController;
import dao.ChessDao;
import domain.chessboard.ChessBoardFactory;
import domain.chessgame.ChessGame;
import service.ChessService;

public class ChessGameApplication {

    public static void main(String[] args) {
        final ChessService chessService = new ChessService(new ChessGame(ChessBoardFactory.generate()), new ChessDao());
        final ChessGameController chessGameController = new ChessGameController(chessService);
        chessGameController.run();
    }

}
