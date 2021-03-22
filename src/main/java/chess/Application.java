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
        while (!chessGame.state().isFinished()) {
            turn(chessGame, command());
            printBoard(chessGame);
        }
    }

    private static void turn(ChessGame chessGame, Command command) {
        try {
            chessGame.execute(command);
        } catch (ChessException e) {
            OutputView.printError(e);
            turn(chessGame, command());
        }
    }

    private static void printBoard(ChessGame chessGame) {
        OutputView.printBoard(chessGame);

        // TODO 디미터 .없애기
        if (chessGame.state().isGameSet()) {
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
