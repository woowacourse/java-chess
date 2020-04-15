package chess.controller;

import chess.Board;
import chess.Command;
import chess.Scores;
import chess.exception.CommandException;
import chess.exception.InvalidMovementException;
import chess.position.Position;
import chess.strategy.NormalInitStrategy;
import chess.view.ConsoleInputView;
import chess.view.ConsoleOutputView;

public class ConsoleChessController {
    private final Board board = new Board(new NormalInitStrategy());

    public void run() {
        start();
        while (board.isNotFinished()) {
            play();
        }
    }

    private void start() {
        ConsoleOutputView.printGameIntro();
        if (requestCommand().isEnd()) {
            board.finishGame();
            return;
        }
        ConsoleOutputView.printBoard(board.getPieces());
    }

    private Command requestCommand() {
        try {
            return Command.beforeGameCommandOf(ConsoleInputView.requestCommand());
        } catch (CommandException e) {
            ConsoleOutputView.printExceptionMessage(e.getMessage());
            return requestCommand();
        }
    }

    private void play() {
        try {
            Command command = Command.inGameCommandOf(ConsoleInputView.requestCommand());
            proceedIfCommandIsMove(command);
            printScoresIfCommandIsStatus(command);
        } catch (CommandException | InvalidMovementException e) {
            ConsoleOutputView.printExceptionMessage(e.getMessage());
            play();
        }
    }

    private void proceedIfCommandIsMove(Command command) {
        if (command.isMove()) {
            Position source = Position.of(ConsoleInputView.requestPosition());
            Position target = Position.of(ConsoleInputView.requestPosition());
            board.moveIfPossible(source, target);
            ConsoleOutputView.printBoard(board.getPieces());
        }
    }

    private void printScoresIfCommandIsStatus(Command command) {
        if (command.isStatus()) {
            ConsoleOutputView.printScores(Scores.calculateScores(board));
        }
    }
}
