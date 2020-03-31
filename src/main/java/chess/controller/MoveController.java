package chess.controller;

import chess.domain.ChessRunner;

public class MoveController extends GameController {
    private static final String DELIMITER = " ";
    private static final int COMMANDS_SIZE = 2;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    public MoveController() {
        super();
    }

    @Override
    public void execute(ChessRunner chessRunner, String input) {
        String[] commands = input.split(DELIMITER);
        if (commands.length < COMMANDS_SIZE) {
            throw new IllegalArgumentException("잘못된 이동 명령어를 입력하였습니다.");
        }

        String source = commands[SOURCE_INDEX];
        String target = commands[TARGET_INDEX];
        chessRunner.update(source, target);
        printBoard(chessRunner.getBoard());
    }
}
