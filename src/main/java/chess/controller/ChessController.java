package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.position.Position;
import chess.exception.ChessException;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public static void start() {
        OutputView.startGame();
        ChessGame chessGame = new ChessGame(Board.getGamingBoard());
        if (Command.from(InputView.command()) == Command.START) {
            startGame(chessGame);
        }
    }

    private static void startGame(ChessGame chessGame) {
        chessGame.start();
        printBoard(chessGame);

        while (!chessGame.isFinished()) {
            turn(chessGame);
        }
    }

    private static void printBoard(ChessGame chessGame) {
        OutputView.print(chessGame.board(), chessGame.side());

        if (chessGame.isGameSet()) {
            OutputView.printWinner(chessGame.state().winner());
        }
    }

    private static void turn(ChessGame chessGame) {
        try {
            execute(InputView.command(), chessGame);
            printBoard(chessGame);
        } catch (ChessException e) {
            OutputView.printError(e);
            turn(chessGame);
        }
    }

    private static void execute(String input, ChessGame chessGame) {

        Command command = Command.from(input);
        if (Command.MOVE == command) {
            Position[] positions = Command.positions(input);
            chessGame.move(positions[0], positions[1]);
        }

        if (Command.STATUS == command) {
            chessGame.status();
            OutputView.print(chessGame.score());
        }

        if (Command.END == command) {
            chessGame.end();
        }
    }
}
