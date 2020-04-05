import chess.command.*;
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
        if (EndCommand.isEnd(commandInput)) {
            return new EndCommand(commandInput);
        }
        if (MoveCommand.isMove(commandInput)) {
            return MoveCommand.of(commandInput, chessGame);
        }
        if (StatusCommand.isStatus(commandInput)) {
            return new StatusCommand(commandInput);
        }
        return new StartCommand(commandInput);
    }

    private static void printResult(ChessGame chessGame) {
        ChessResult chessResult = chessGame.findWinner();
        OutputView.print(chessResult);
    }
}
