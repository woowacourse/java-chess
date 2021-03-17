package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView.printGameStartMessage();
        List<String> playerCommand = InputView.inputPlayerCommand();
        Command command = Command.findCommand(playerCommand.get(0));

        Board board = Board.getInstance();
        board.initialize();
        if (command == Command.START) {
            startChessGame(board);
        }
    }

    private static void startChessGame(Board board) {
        OutputView.printBoard(board);
        List<String> playerCommand = InputView.inputPlayerCommand();
        Command command = Command.findCommand(playerCommand.get(0));
        while (command != Command.END) {
            executeCommand(command, board, playerCommand);
            OutputView.printBoard(board);
            playerCommand = InputView.inputPlayerCommand();
            command = Command.findCommand(playerCommand.get(0));
        }
    }

    private static void executeCommand(Command command, Board board, List<String> playerCommand) {
        if (command == Command.MOVE) {
            System.out.println("current : " + playerCommand.get(1));
            System.out.println("target : " + playerCommand.get(2));
        }
    }
}
