import chess.command.Command;
import chess.command.End;
import chess.command.Move;
import chess.command.Start;
import chess.game.ChessGame;
import chess.result.ChessResult;
import chess.progress.Progress;
import view.InputView;
import view.OutputView;

public class ChessController {
    public static void main(String[] args) {
        OutputView.printInformation();
        Progress progress = Progress.CONTINUE;
        ChessGame chessGame = new ChessGame();
        while (progress.isNotEnd()) {
            progress = getProgress(chessGame);
            OutputView.printPresentPlayer(chessGame.getTurn());
        }
        // TODO : 바로 종료 했을 때 java.util.NoSuchElementException: No value present 에러 발생
        printResult(chessGame);
    }

    private static Progress getProgress(ChessGame chessGame) {
        Command command = inputCommand(chessGame);
        Progress progress = chessGame.doOneCommand(command);
        while (progress.isError()) {
            OutputView.printMoveErrorMessage();
            command = inputCommand(chessGame);
            progress = chessGame.doOneCommand(command);
        }
        if (progress.isNotEnd()) {
            OutputView.printBoard(chessGame.getChessBoard());
        }

        chessGame.changeTurn();

        return progress;
    }

    private static Command inputCommand(ChessGame chessGame) {
        String commandInput = InputView.inputCommand();
        if (commandInput.equals("end")) {
            return new End(commandInput);
        }
        if (commandInput.substring(0, 4).equals("move")) {
            return new Move(commandInput, chessGame);
        }
        return new Start(commandInput);
    }

    private static void printResult(ChessGame chessGame) {
        ChessResult chessResult = chessGame.findWinner();
        OutputView.print(chessResult);
    }
}
