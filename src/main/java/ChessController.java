import chess.command.*;
import chess.game.ChessGame;
import chess.result.ChessResult;
import chess.progress.Progress;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.function.Predicate;

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
        if (End.isEnd(commandInput)) {
            return new End(commandInput);
        }
        if (Move.isMove(commandInput)) {
            return new Move(commandInput, chessGame);
        }
        if (Status.isStatus(commandInput)) {
            return new Status(commandInput);
        }
        return new Start(commandInput);
    }

    private static void printResult(ChessGame chessGame) {
        ChessResult chessResult = chessGame.findWinner();
        OutputView.print(chessResult);
    }
}
