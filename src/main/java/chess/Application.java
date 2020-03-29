package chess;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.exception.InvalidMovementException;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        ChessService service = new ChessService();
        Command command;

        OutputView.printStart();
        do {
            command = Command.from(InputView.receiveCommand());
            if (command.isStart()) {
                OutputView.printBoard(service.placeInitialPieces());
            }
            if (command.isMove()) {
                executeMovement(service, command);
            }
            if (command.isStatus()) {
                OutputView.printScore(service.calculateResult());
            }
        } while (command.isNotEnd() && service.checkGameNotFinished());
    }

    private static void executeMovement(ChessService service, Command command) {
        try {
            Board board = service.move(command.getSource(), command.getTarget());
            OutputView.printBoard(board);
        } catch (IllegalArgumentException | InvalidMovementException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }
}
