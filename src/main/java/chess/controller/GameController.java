package chess.controller;

import chess.domains.board.Board;
import chess.service.ChessService;
import chess.view.OutputView;

public class GameController {
    public static final String DELIMITER = " ";
    public static final int SELECTED_COMMAND = 0;
    public static final int SOURCE_POSITION = 1;
    public static final int TARGET_POSITION = 2;

    public static void printInitialBoard(Board board) {
        OutputView.printBoard(board.showBoard());
    }

    public static void command(String commandMsg, Board board) {
        OutputView.printTeamColor(board.getTeamColor());

        String[] commands = commandMsg.split(DELIMITER);
        Command command = Command.findCommand(commands[SELECTED_COMMAND]);

        if (command == Command.STATUS) {
            printStatus(board);
        } else if (command == Command.MOVE) {
            move(board, commands[SOURCE_POSITION], commands[TARGET_POSITION]);
        }
    }

    private static void printStatus(Board board) {
        double score = ChessService.calculateScore(board);
        OutputView.printScore(score);
    }

    private static void move(Board board, String source, String target) {
        ChessService.move(board, source, target);
        OutputView.printBoard(board.showBoard());
    }
}
