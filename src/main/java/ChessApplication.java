import controller.BoardController;
import dao.BoardDao;
import domain.Board;
import domain.Turn;
import exception.CheckMateException;
import exception.GameFinishedException;
import view.InputView;
import view.OutputView;
import view.ScannerInputReader;

public class ChessApplication {
    public static void main(String[] args) {
        BoardController controller = new BoardController(
                "게임65",
                new Board(),
                new BoardDao(),
                new OutputView(),
                new InputView(new ScannerInputReader()));
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
