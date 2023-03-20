import domain.ChessBoard;
import domain.Square;
import java.util.function.Supplier;
import view.Command;
import view.End;
import view.InputView;
import view.Move;
import view.OutputView;
import view.Start;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;

    public MainController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    private <T> T repeatInputReader(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return repeatInputReader(inputReader);
        }
    }

    public void run() {
        ChessBoard chessBoard = new ChessBoard();
        inputView.printStartMessage();
        Command command = repeatInputReader(inputView::readCommand);
        if (command instanceof Start) {
            printChessBoard(chessBoard);
            do {
                command = repeat(inputView::readCommand);
                executeMoveCommand(chessBoard, command);
            } while (!(command instanceof End));
        }
    }

    private void executeMoveCommand(ChessBoard chessBoard, Command command) {
        if (command instanceof Move) {
            try {
                Square source = ((Move) command).getSource();
                Square target = ((Move) command).getTarget();
                chessBoard.move(source, target);
                printChessBoard(chessBoard);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
                command = repeatInputReader(inputView::readCommand);
                executeMoveCommand(chessBoard, command);
            }
        }
    }

    private void printChessBoard(ChessBoard chessBoard) {
        outputView.printChessBoard(chessBoard);
    }
}
