package chess.controller;

import chess.Board;
import chess.Command;
import chess.CommandException;
import chess.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    private final Board board = Board.createInitialBoard();

    public void run() {
        start();
        while (board.isNotFinished()) {
            play();
        }
    }

    private void start() {
        OutputView.printGameIntro();
        requestCommand();
        OutputView.printBoard(board);
    }

    private void requestCommand() {
        try {
            Command command = Command.beforeGameCommandOf(InputView.requestCommand());
            terminateIfCommandIsEnd(command);
        } catch (CommandException e) {
            OutputView.printExceptionMessage(e.getMessage());
            requestCommand();
        }
    }

    private void terminateIfCommandIsEnd(Command command) {
        if (command.isEnd()) {
            System.exit(0);
        }
    }

    private void play() {
        try {
            Command command = Command.inGameCommandOf(InputView.requestCommand());
            proceedIfCommandIsMove(command);
            printScoresIfCommandIsStatus(command);
        } catch (CommandException | IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            play();
        }
    }

    private void proceedIfCommandIsMove(Command command) {
        if (command.isMove()) {
            Position source = Position.of(InputView.requestPosition());
            Position target = Position.of(InputView.requestPosition());
            board.moveIfPossible(source, target);
            OutputView.printBoard(board);
        }
    }

    private void printScoresIfCommandIsStatus(Command command) {
        if (command.isStatus()) {
            OutputView.printScores(board.calculateScores());
        }
    }
}
