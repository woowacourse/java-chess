package chess;

import chess.domain.board.Board;
import chess.domain.game.Command;
import chess.domain.move.MovingInfo;
import chess.domain.move.Position;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

import static chess.domain.game.Command.*;

public class Application {
    public static void main(String[] args) {
        OutputView.printInitialMessage();
        run();
    }

    private static void run() {
        Board board = BoardFactory.createBoard();
        String command = "";

        while (!END.equals(command) && !board.isGameEnd()) {
            command = getCommand();
            executeCommand(board, command);
        }
    }

    private static String getCommand() {
        String command = InputView.inputCommand();

        if (validateCommand(command)) {
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
            MovingInfo movingInfo = new MovingInfo(Position.of(splitCommand[1]), Position.of(splitCommand[2]));

            board.move(movingInfo);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            OutputView.printWrongPositionMessage();
        }
    }

    private static boolean validateCommand(String nowCommand) {
        String splitCommand = nowCommand.split(" ")[0];

        return Command.isExistCommand(splitCommand);
    }
}
