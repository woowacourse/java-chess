package chess;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.command.Command;
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

        while(!command.equals("end") && !chessBoard.isGameEnd()){
            command = getCommand();
            executeCommand(chessBoard, command);
        }
    }

    private static String getCommand() {
        String command = InputView.inputCommand();

        if(validateCommand(command)){
            return command;
        }
        OutputView.printWrongCommandMessage();
        return getCommand();
    }

    private static void executeCommand(ChessBoard chessBoard, String command) {
        String[] splitCommand = command.split(" ");

        if(splitCommand[0].equals("move")){
            chessBoard.move(Position.of(splitCommand[1]), Position.of(splitCommand[2]) ) ;
        }
        if(splitCommand[0].equals("status")){
            OutputView.printScore(chessBoard);
        }
        OutputView.printBoard(chessBoard);
    }

    private static boolean validateCommand(String nowCommand) {
        String splitCommand = nowCommand.split(" ")[0];

        return Command.isExistCommand(splitCommand);
    }
}
