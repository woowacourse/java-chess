import domain.board.Board;
import view.InputView;
import view.OutputView;

public class ChessApplication {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        outputView.printStartMessage();

        while (inputView.readStartCommand()) {
            outputView.printBoard(Board.initialize().getSquares());
        }
    }
}
