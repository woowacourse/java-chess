import controller.BoardController;
import controller.ChessController;
import dao.BoardDao;
import domain.Turn;
import dto.ChessGame;
import exception.CheckMateException;
import exception.GameFinishedException;
import view.InputView;
import view.OutputView;

public class ChessApplication {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();
        BoardDao boardDao = new BoardDao();

        ChessController chessController = new ChessController(inputView, outputView, boardDao);
        ChessGame chessGame = chessController.findChessGame();

        BoardController boardController = new BoardController(chessGame, boardDao, outputView, inputView);
        boardController.initializeBoard();

        play(boardController);
    }

    private static void play(BoardController controller) {
        Turn turn = Turn.WHITE;
        while (true) {
            try {
                controller.executeByCommand(turn);
                turn = turn.switchTurn();
            } catch (GameFinishedException | CheckMateException e) {
                return;
            }
        }
    }
}
