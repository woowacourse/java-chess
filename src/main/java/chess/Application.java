package chess;

import chess.domain.board.ChessBoard;
import chess.domain.game.Command;
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
        ChessBoard chessBoard = new ChessBoard(BoardFactory.createBoard());
        String command = "";

        while (!command.equals("end") && !chessBoard.isGameEnd()) {
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

        if (splitCommand[0].equals("move")) {
            MovingInfo movingInfo = new MovingInfo(Position.of(splitCommand[1]), Position.of(splitCommand[2]));
            tryMove(chessBoard, movingInfo);
        }

        if (splitCommand[0].equals("status")) {
            OutputView.printScore(chessBoard);
        }
        OutputView.printBoard(chessBoard);
    }

    private static void tryMove(ChessBoard chessBoard, MovingInfo movingInfo) {
        try {
            chessBoard.move(movingInfo);
        }
        catch (IllegalArgumentException e){
            OutputView.printWrongCommandMessage();
        }
    }

    private static boolean validateCommand(String nowCommand) {
        String splitCommand = nowCommand.split(" ")[0];

        return Command.isExistCommand(splitCommand);
    }
}
