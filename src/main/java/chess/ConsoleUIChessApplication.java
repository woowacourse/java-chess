package chess;

import chess.controller.Command;
import chess.controller.GameController;
import chess.domains.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        OutputView.printStartMSG();
        Command command = Command.findCommand(InputView.inputCommand());

        if (command == Command.START) {
            Board board = new Board();
            GameController.printInitialBoard(board);
            startGame(board);
        } else if (command == Command.END) {
            OutputView.printEnd();
        }
    }

    private static void startGame(Board board) {
        while (!board.isGameOver()) {
            GameController.printTurn(board);
            String command = InputView.inputCommand();
            GameController.command(command, board);
        }
    }


}
