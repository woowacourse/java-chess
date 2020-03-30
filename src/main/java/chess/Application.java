package chess;

import chess.domain.board.Board;
import chess.domain.game.Command;
import chess.domain.game.GameStatus;
import chess.domain.move.MovingInfo;
import chess.domain.move.Position;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView.printInitialMessage();
        run();
    }

    private static void run() {
        Board board = BoardFactory.createBoard();
        String command = "";
        GameStatus gameStatus = board.getGameStatus();

        while (!Command.isEnd(command) && !gameStatus.isGameEnd()) {
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
        if (Command.isMove(command)) {
            tryMove(command, board);
        }
        if (Command.isStatus(command)) {
            OutputView.printScore(board);
        }
        OutputView.printBoard(board);
    }

    private static void tryMove(String command, Board board) {
        try {
            Position startPosition = Command.makeStartPosition(command);
            Position targetPosition = Command.makeTargetPosition(command);
            MovingInfo movingInfo = new MovingInfo(startPosition, targetPosition);

            board.move(movingInfo);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException error) {
            OutputView.printWrongPositionMessage(error);
        }
    }
}
