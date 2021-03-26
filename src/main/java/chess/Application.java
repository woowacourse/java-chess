package chess;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.game.ChessGame;
import chess.domain.gamestate.Ready;
import chess.exception.ChessException;
import chess.exception.InvalidCommandException;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.startGame();
        ChessGame chessGame = new ChessGame(new Ready(Board.createGamingBoard()));

        if (command().isStart()) {
            chessGame.start();
            printBoard(chessGame);
            runGame(chessGame);
        }
    }

    private static void runGame(ChessGame chessGame) {
        while (!chessGame.isFinished()) {
            turn(chessGame);
            printBoard(chessGame);
        }
    }

    private static void turn(ChessGame chessGame) {
        try {
            Command command = command();
            chessGame.execute(command);
            printStatus(chessGame, command);
        } catch (ChessException e) {
            OutputView.printError(e);
            turn(chessGame);
        }
    }

    private static void printStatus(ChessGame chessGame, Command command) {
        if (command.isStatus()) {
            OutputView.print(chessGame.score());
        }
    }

    private static void printBoard(ChessGame chessGame) {
        OutputView.printBoard(chessGame);

        if (chessGame.isGameSet()) {
            OutputView.printWinner(chessGame.winner());
        }
    }

    private static Command command() {
        try {
            return Command.from(InputView.command());
        } catch (InvalidCommandException e) {
            OutputView.printError(e);
            return command();
        }
    }
}
