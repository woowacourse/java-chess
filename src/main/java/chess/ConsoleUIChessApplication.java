package chess;

import chess.controller.Command;
import chess.controller.GameController;
import chess.domains.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleUIChessApplication {
    public static void main(String[] args) {
        OutputView.printStartMSG();
        String firstCommand = InputView.inputCommand();

        if (Command.isStart(firstCommand)) {
            Board board = new Board();
            GameController.printInitialBoard(board);
            startGame(board);
        } else if (Command.isEnd(firstCommand)) {
            OutputView.printEnd();
        }
    }

    private static void startGame(Board board) {
        while (!board.isGameOver()) {
            String command = InputView.inputCommand();
            GameController.command(command, board);
        }
    }


}
