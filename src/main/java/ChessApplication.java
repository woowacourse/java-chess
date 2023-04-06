import controller.BoardController;
import controller.ChessController;
import controller.ChessGame;
import dao.BoardDao;
import domain.Turn;
import exception.CheckMateException;
import exception.GameFinishedException;
import view.InputView;
import view.OutputView;
import view.ScannerInputReader;

public class ChessApplication {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView(new ScannerInputReader());
        BoardDao boardDao = new BoardDao();

        ChessController chessController = new ChessController(inputView, outputView, boardDao);
        ChessGame chessGame = chessController.findChessGame();

        BoardController controller = new BoardController(
                chessGame.getId(),
                chessGame.getChessGame(),
                boardDao,
                outputView,
                inputView);
        try {
            controller.initializeBoard();
        } catch (GameFinishedException e) {
            //TODO : 게임이 끝날 때 동작
            return;
        }

        play(controller);
    }

    private static void play(BoardController controller) {
        Turn turn = Turn.WHITE;
        while (true) {
            try {
                controller.executeByCommand(turn);
                turn = turn.switchTurn();
            } catch (GameFinishedException e) {
                //TODO : 게임이 끝날 때 동작
                return;
            } catch (CheckMateException e) {
                controller.checkmate(turn);
                return;
            }
        }
    }
}
