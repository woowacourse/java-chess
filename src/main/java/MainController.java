import domain.ChessGame;
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

    private <T> T repeat(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return repeat(inputReader);
        }
    }

    public void run() {
        ChessGame chessGame = new ChessGame();
        inputView.printStartMessage();
        Command command = repeat(inputView::readCommand);
        if (command instanceof Start) {
            printChessBoard(chessGame);
            do {
                command = repeat(inputView::readCommand);
                executeMoveCommand(chessGame, command);
            } while (!(command instanceof End));
        }
    }

    private void executeMoveCommand(ChessGame chessGame, Command command) {
        if (command instanceof Move) {
            try {
                Square source = ((Move) command).getSource();
                Square target = ((Move) command).getTarget();
                chessGame.move(source, target);
                printChessBoard(chessGame);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
                command = repeat(inputView::readCommand);
                executeMoveCommand(chessGame, command);
            }
        }
    }

    private void printChessBoard(ChessGame chessGame) {
        outputView.printChessBoard(chessGame);
    }
}
