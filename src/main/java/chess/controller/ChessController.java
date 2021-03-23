package chess.controller;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.State;
import chess.exception.ChessException;
import chess.exception.InvalidCommandException;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public static void start() {
        OutputView.startGame();
        ChessGame chessGame = new ChessGame(Board.getGamingBoard());
        Command command = command();
        if (command.isStart()) {
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
            execute(command(), chessGame);
            printBoard(chessGame);
        } catch (ChessException e) {
            OutputView.printError(e);
            turn(chessGame);
        }
    }

    private static void execute(Command command, ChessGame chessGame) {

        if (command.isMove()) {
            chessGame.move(command.source(), command.target());
        }

        if (command.isStatus()) {
            chessGame.status();
            OutputView.print(chessGame.score());
        }

        if (command.isEnd()) {
            chessGame.end();
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
