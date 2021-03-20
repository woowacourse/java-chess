package chess.domain.command;

import chess.domain.ChessGame;

public class MoveOnCommand implements Command {
    private static final String COMMAND_NAME = "move";
    private static final String COMMAND_MESSAGE = "%s 에서 %s로 이동을 하였습니다";

    @Override
    public String run(String input, ChessGame chessGame) {
        String[] splitInput = input.split(" ");
        if (splitInput.length != 3) {
            throw new IndexOutOfBoundsException();
        }
        chessGame.move(splitInput[1], splitInput[2]);
        return String.format(COMMAND_MESSAGE, splitInput[1], splitInput[2]);
    }

    @Override
    public boolean isSameCommand(String command) {
        return COMMAND_NAME.equals(command.split(" ")[0]);
    }
}
