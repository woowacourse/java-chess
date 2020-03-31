package chess.controller;

import chess.domains.board.Board;
import chess.service.ChessService;
import chess.view.OutputView;

public class GameController {
    public static final String DELIMITER = " ";
    public static final int SOURCE_POSITION = 1;
    public static final int TARGET_POSITION = 2;

    public static void printInitialBoard(Board board) {
        OutputView.printBoard(board.showBoard());
    }

    public static void command(String command, Board board) {
        OutputView.printTeamColor(board.getTeamColor());
        if (Command.isStatus(command)) {
            printStatus(board);
        } else if (Command.isMove(command)) {
            String[] moveCommand = command.split(DELIMITER);
            move(board, moveCommand[SOURCE_POSITION], moveCommand[TARGET_POSITION]);
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
