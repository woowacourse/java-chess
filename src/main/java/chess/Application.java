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
        Board board = Board.createEmpty();
        Command command;

        OutputView.printStart();
        do {
            command = Command.from(InputView.receiveCommand());
            if (command.isStart()) {
                board = service.initialize();
                OutputView.printBoard(board);
            }
            if (command.isMove()) {
                try {
                    board = service.move(board, command.getSource(), command.getTarget());
                } catch (IllegalArgumentException | InvalidMovementException e) {
                    OutputView.printExceptionMessage(e.getMessage());
                    continue;
                }
                OutputView.printBoard(board);
            }
            if (command.isStatus()) {
                OutputView.printScore(service.calculateScore(board));
            }
        } while (command.isNotEnd() && service.checkGameNotFinished(board));
    }
}
