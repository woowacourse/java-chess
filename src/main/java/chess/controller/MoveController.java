package chess.controller;

import chess.domain.ChessRunner;

public class MoveController extends GameController {
    private static final String DELIMITER = " ";
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    public MoveController() {
        super();
    }

    @Override
    public void execute(ChessRunner chessRunner, String input) {
        String[] commands = input.split(DELIMITER);
        String source = commands[SOURCE_INDEX];
        String target = commands[TARGET_INDEX];
        chessRunner.update(source, target);
        printBoard(chessRunner.getBoard());
    }
}
