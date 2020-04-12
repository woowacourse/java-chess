package chess;

import java.sql.SQLException;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.command.Command;
import chess.domain.player.User;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        ChessService service = new ChessService();
        OutputView.printBoard(service.getBoard(User.EMPTY_BOARD_USER));
        User blackUser = createBlackUser();
        User whiteUser = createWhiteUser();
        Command command;

        OutputView.printStart();
        do {
            command = receiveCommand();
            if (command.isStart()) {
                OutputView.printBoard(BoardFactory.createInitialBoard(blackUser, whiteUser));
            }
            if (command.isMove()) {
                executeMovement(service, command);
            }
            if (command.isStatus()) {
                OutputView.printScore(service.calculateResult(blackUser));
            }
        } while (command.isNotEnd() && service.checkGameNotFinished(blackUser));
    }

    private static User createBlackUser() {
        try {
            return new User(InputView.receiveBlackUser());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return createBlackUser();
        }
    }

    private static User createWhiteUser() {
        try {
            return new User(InputView.receiveBlackUser());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return createBlackUser();
        }
    }

    private static Command receiveCommand() {
        try {
            return Command.from(InputView.receiveCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return receiveCommand();
        }
    }

    private static void executeMovement(ChessService service, Command command) {
        try {
            Board board = service.move(new User("1"), command.getSource(), command.getTarget());
            OutputView.printBoard(board);
        } catch (RuntimeException | SQLException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }
}
