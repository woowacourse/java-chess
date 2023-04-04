import controller.ChessController;
import domain.Board;
import domain.Turn;
import exception.CheckMateException;
import exception.GameFinishedException;
import view.InputView;
import view.OutputView;
import view.ScannerInputReader;

public class ChessApplication {
    public static void main(String[] args) {
        ChessController controller = new ChessController(
                new Board(),
                new InputView(new ScannerInputReader()),
                new OutputView());

        try {
            controller.initializeBoard();
        } catch (GameFinishedException e) {
            //TODO : 게임이 끝날 때 동작
            return;
        }

        play(controller);
    }

    private static void play(ChessController controller) {
        Turn turn = Turn.WHITE;
        while (true) {
            try {
                controller.executeByCommand(turn);
                turn = turn.switchTurn();
            } catch (GameFinishedException e) {
                //TODO : 게임이 끝날 때 동작
                return;
            } catch (CheckMateException e) {
                System.out.printf("게임 끝 ~~ 이긴 팀 : %s%n", e.getTurn());
                return;
            }
        }
    }
}
