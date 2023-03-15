import domain.ChessBoard;
import view.Command;
import view.InputView;
import view.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;

    public MainController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        ChessBoard chessBoard = new ChessBoard();
        Command command = inputView.readStartOrEnd();
        printChessBoard(chessBoard, command);
    }

    private void printChessBoard(ChessBoard chessBoard, Command command) {
        if (command == Command.START) {
            outputView.printChessBoard(chessBoard);
        }
    }
}
