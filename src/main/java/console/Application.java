package console;

import chess.ChessGame;
import console.view.InputView;
import console.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printInitChessGameMessage();
        String command = inputCommand();

        while (true) {
            if (command.equals("start")) {
                ChessGame chessGame = new ChessGame();
                OutputView.printChessBoard(chessGame.getSquare());
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
