package console;

import chess.ChessBoard;
import console.view.InputView;
import console.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printInitChessGameMessage();
        String command = inputCommand();

        while (true) {
            if (command.equals("start")) {
                ChessBoard chessBoard = new ChessBoard();
                OutputView.printChessBoard(chessBoard.getPieces());
            }

            if (command.equals("end")) {
                return;
            }

            command = inputCommand();
        }
    }

    private static String inputCommand() {
        try {
            return InputView.inputCommand();
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommand();
        }
    }
}
