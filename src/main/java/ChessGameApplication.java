import controller.ChessGameController;
import controller.GameStatus;
import dao.ChessDao;
import domain.chessboard.ChessBoardFactory;
import domain.chessgame.ChessGame;
import service.ChessService;

public class ChessGameApplication {

    public static void main(String[] args) {
        final ChessService chessService = new ChessService(new ChessGame(ChessBoardFactory.generate()), new ChessDao());
        final GameStatus gameStatus = new GameStatus(chessService);

        final ChessGameController chessGameController = new ChessGameController(gameStatus);
        chessGameController.run();
    }

}
