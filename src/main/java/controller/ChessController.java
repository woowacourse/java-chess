package controller;

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
        CommandMapper commandMapper = new CommandMapper();
        while (progress.isNotEnd()) {
            progress = getProgress(chessGame, commandMapper);
            OutputView.printPresentPlayer(chessGame.getTurn());
        }
        printResult(chessGame);
    }

    private static Progress getProgress(ChessGame chessGame, CommandMapper commandMapper) {
        Command command = inputCommand(chessGame, commandMapper);
        Progress progress = chessGame.doOneCommand(command);
        while (progress.isError()) {
            OutputView.printMoveErrorMessage();
            command = inputCommand(chessGame, commandMapper);
            progress = chessGame.doOneCommand(command);
        }
        if (progress.isNotEnd()) {
            OutputView.printBoard(chessGame.getChessBoard());
        }

        chessGame.changeTurn();

        return progress;
    }

    private static Command inputCommand(ChessGame chessGame, CommandMapper commandMapper) {
        String commandInput = InputView.inputCommand();
        return commandMapper.getCommand(commandInput, chessGame);
    }

    private static void printResult(ChessGame chessGame) {
        ChessResult chessResult = chessGame.findWinner();
        OutputView.print(chessResult);
    }
}
