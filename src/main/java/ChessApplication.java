import controller.ChessController;
import domain.Board;
import exception.GameFinishedException;
import view.InputView;
import view.OutputView;
import view.ScannerInputReader;

import java.util.Optional;

public class ChessApplication {
    public static void main(String[] args) {
        ChessController controller = new ChessController(
                new InputView(new ScannerInputReader()),
                new OutputView());

        Optional<Board> boardOptional = controller.makeBoard();
        boardOptional.ifPresent(board -> play(board, controller));
    }

    private static void play(Board board, ChessController controller) {
        while (true) {
            try {
                controller.movePiece(board);
            } catch (GameFinishedException e) {
                //TODO : 게임이 끝날 때 동작
                return;
            }
        }
    }
}
