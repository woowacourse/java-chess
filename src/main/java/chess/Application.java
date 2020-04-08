package chess;

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
        OutputView.printBoard(service.createEmpty());
        Command command;

        OutputView.printStart();
        do {
            command = receiveCommand();
            if (command.isStart()) {
                OutputView.printBoard(BoardFactory.createInitialBoard(new User("1"), new User("2")));
            }
            if (command.isMove()) {
                executeMovement(service, command);
            }
            if (command.isStatus()) {
                OutputView.printScore(service.calculateResult(new User("1")));
            }
        } while (command.isNotEnd() && service.checkGameNotFinished(new User("1")));
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
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }
}
