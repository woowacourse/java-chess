package chess.domain.command;

import chess.domain.ChessGame;

public class MoveOnCommand implements Command {
    private static final String DELIMITER = " ";
    private static final int MOVE_COMMAND_LENGTH = 3;
    private static final int MOVE_COMMAND_TARGET = 1;
    private static final int MOVE_COMMAND_DESTINATION = 2;
    private static final String COMMAND_NAME = "move";
    private static final String COMMAND_MESSAGE = "%s 에서 %s로 이동을 하였습니다";

    public String run(ChessGame chessGame, String input) {
        String[] splitInput = input.split(DELIMITER);
        if (splitInput.length != MOVE_COMMAND_LENGTH) {
            throw new IndexOutOfBoundsException();
        }
        chessGame.move(splitInput[MOVE_COMMAND_TARGET], splitInput[MOVE_COMMAND_DESTINATION]);
        return String.format(COMMAND_MESSAGE, splitInput[MOVE_COMMAND_TARGET], splitInput[MOVE_COMMAND_DESTINATION]);
    }

    @Override
    public boolean isSameCommand(String command) {
        return COMMAND_NAME.equals(command.split(" ")[0]);
    }
}
