package chess;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Board board = Board.createEmpty();
        OutputView.printBoard(board);
        Command command;

        OutputView.printStart();
        do {
            command = receiveCommand();
            if (command.isStart()) {
                OutputView.printBoard(board.placeInitialPieces());
            }
            if (command.isMove()) {
                executeMovement(board, command);
            }
            if (command.isStatus()) {
                OutputView.printScore(board.calculateResult());
            }
        } while (command.isNotEnd() && board.isNotFinished());
    }

    private static Command receiveCommand() {
        try {
            return Command.from(InputView.receiveCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return receiveCommand();
        }
    }

    private static void executeMovement(Board board, Command command) {
        try {
            board = board.move(command.getSource(), command.getTarget());
            OutputView.printBoard(board);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }
}
