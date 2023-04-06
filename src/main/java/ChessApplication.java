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

        BoardController controller = new BoardController(
                chessGame,
                boardDao,
                outputView,
                inputView);
        try {
            controller.initializeBoard();
            play(controller, outputView);
        } catch (GameFinishedException e) {
            outputView.printGameEndMessage();
        }
    }

    private static void play(BoardController controller, OutputView outputView) {
        Turn turn = Turn.WHITE;
        while (true) {
            try {
                controller.executeByCommand(turn);
                turn = turn.switchTurn();
            } catch (GameFinishedException e) {
                outputView.printGameEndMessage();
                return;
            } catch (CheckMateException e) {
                controller.checkmate(turn);
                return;
            }
        }
    }
}
