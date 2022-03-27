package console;

import chess.ChessBoard;
import console.view.*;

public class Application {

    public static void main(String[] args) {
        OutputView.printInitChessGameMessage();
        Command command = inputCommand();
        ChessBoard chessBoard = null;

        while (!command.isFinished()) {
            chessBoard = executeCommand(command, chessBoard);
            command = inputCommand();
        }
    }

    private static ChessBoard executeCommand(Command command, ChessBoard chessBoard) {
        try {
            chessBoard = command.execute(chessBoard);
            OutputView.printChessBoard(chessBoard.getPieces());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            OutputView.printChessBoard(chessBoard.getPieces());
        }
        return chessBoard;
    }

    private static Command inputCommand() {
        try {
            return InputView.inputCommand();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommand();
        }
    }
}
