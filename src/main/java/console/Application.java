package console;

import chess.ChessBoard;
import chess.game.ChessGame;
import chess.command.Command;
import chess.state.Ready;
import console.view.InputView;
import console.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printInitChessGameMessage();
        Command command = inputCommand();
        ChessGame chessGame = new ChessGame(new Ready(ChessBoard.createChessBoard()));

        if (command.isStart()) {
            chessGame.start();
            printChessBoard(chessGame);
            playChessGame(chessGame);
        }
    }

    private static void playChessGame(ChessGame chessGame) {
        while (!chessGame.isFinished()) {
            tryOneTurn(chessGame);
            printChessBoard(chessGame);
        }
    }

    private static void tryOneTurn(ChessGame chessGame) {
        try {
            Command command = inputCommand();
            chessGame.execute(command);
            if (command.isStatus()) {
                OutputView.printScores(chessGame.score());
            }
        } catch (RuntimeException e) {
            OutputView.printError(e.getMessage());
            tryOneTurn(chessGame);
        }
    }

    private static void printChessBoard(ChessGame chessGame) {
        OutputView.printChessBoard(chessGame.chessBoard());

        if (chessGame.isGameEnd()) {
            OutputView.printWinner(chessGame.winner());
        }
    }

    private static Command inputCommand() {
        try {
            return Command.from(InputView.inputCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return inputCommand();
        }
    }
}
