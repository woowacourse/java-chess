package chess.controller;

import chess.Board;
import chess.Command;
import chess.exception.CommandException;
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
        if (requestCommand().isEnd()) {
            board.finishGame();
            return;
        }
        OutputView.printBoard(board.getPieces());
    }

    private Command requestCommand() {
        try {
            return Command.beforeGameCommandOf(InputView.requestCommand());
        } catch (CommandException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return requestCommand();
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
            OutputView.printBoard(board.getPieces());
        }
    }

    private void printScoresIfCommandIsStatus(Command command) {
        if (command.isStatus()) {
            OutputView.printScores(board.calculateScores());
        }
    }
}
