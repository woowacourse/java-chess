package chess;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.exception.InvalidMovementException;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        ChessService service = new ChessService();
        Board board = Board.EMPTY;
        Command command;

        OutputView.printStart();
        do {
            command = Command.from(InputView.receiveCommand());
            if (command.isStart()) {
                board = service.initialize();
                OutputView.printBoard(board);
            }
            if (command.isMove()) {
                List<String> flags = command.getFlags();
                try {
                    board = service.move(board, flags.get(0), flags.get(1));
                } catch (IllegalArgumentException | InvalidMovementException e) {
                    OutputView.printExceptionMessage(e.getMessage());
                    continue;
                }
                OutputView.printBoard(board);
            }
        } while (command.isNotEnd());
    }
}
