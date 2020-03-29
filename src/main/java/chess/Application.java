package chess;

import chess.domain.board.Board;
import chess.domain.game.Command;
import chess.domain.game.GameStatus;
import chess.domain.move.MovingInfo;
import chess.domain.move.Position;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

import static chess.domain.game.Command.*;

public class Application {
    public static void main(String[] args) {
        OutputView.printInitialMessage();
        GameStatus.initialize();
        run();
    }

    private static void run() {
        Board board = BoardFactory.createBoard();
        String command = "";

        while (!END.equals(command) && !GameStatus.isGameEnd()) {
            command = getCommand();
            executeCommand(board, command);
        }
    }

    private static String getCommand() {
        String command = InputView.inputCommand();

        if (Command.isExistCommand(command)) {
            return command;
        }
        OutputView.printWrongCommandMessage();
        return getCommand();
    }

    private static void executeCommand(Board board, String command) {
        String[] splitCommand = command.split(" ");

        if (MOVE.equals(splitCommand[0])) {
            tryMove(splitCommand, board);
        }
        if (STATUS.equals(splitCommand[0])) {
            OutputView.printScore(board);
        }
        OutputView.printBoard(board);
    }

    private static void tryMove(String[] splitCommand, Board board) {
        try {
            Position startPosition = Position.of(splitCommand[1]);
            Position targetPosition = Position.of(splitCommand[2]);
            MovingInfo movingInfo = new MovingInfo(startPosition, targetPosition);

            board.move(movingInfo);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException error) {
            OutputView.printWrongPositionMessage(error);
        }
    }
}
