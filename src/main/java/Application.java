import chess.Board;
import chess.GameSwitch;
import chess.dto.PositionResponseDto;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printStartGame();
        if (InputView.inputStartOrEnd() == GameSwitch.START) {
            Board board = Board.create();
            OutputView.printBoard(PositionResponseDto.of(), board);
        }
    }
}
