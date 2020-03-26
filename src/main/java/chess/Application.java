package chess;

import chess.domain.board.ChessBoard;
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
        ChessBoard chessBoard = new ChessBoard(BoardFactory.createBoard());
        String command = "";
        while (!END.equals(command) && !chessBoard.isGameEnd()) {
            command = getCommand();
            executeCommand(chessBoard, command);
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

    private static void executeCommand(ChessBoard chessBoard, String command) {
        String[] splitCommand = command.split(" ");

        if (MOVE.equals(splitCommand[0])) {
            tryMove(splitCommand, chessBoard);
        }
        if (STATUS.equals(splitCommand[0])) {
            OutputView.printScore(chessBoard);
        }
        OutputView.printBoard(chessBoard);
    }

    private static void tryMove(String[] splitCommand, ChessBoard chessBoard) {
        try {
            MovingInfo movingInfo = new MovingInfo(Position.of(splitCommand[1]), Position.of(splitCommand[2]));

            chessBoard.move(movingInfo);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            OutputView.printWrongPositionMessage();
        }
    }

    private static boolean validateCommand(String nowCommand) {
        String splitCommand = nowCommand.split(" ")[0];

        return Command.isExistCommand(splitCommand);
    }
}
