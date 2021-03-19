package chess.domain.command;

import chess.domain.ChessGame;

public class MoveOnCommand extends PozCommand {
    private static final String COMMAND_NAME = "move";
    private static final String COMMAND_MESSAGE = "%s 에서 %s로 이동을 하였습니다";

    public MoveOnCommand(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public String run(String input) {
        String[] splitInput = input.split(" ");
        if (splitInput.length != 3) {
            throw new IndexOutOfBoundsException();
        }
        getChessGame().move(splitInput[1], splitInput[2]);
        return String.format(COMMAND_MESSAGE, splitInput[1], splitInput[2]);
    }

    @Override
    public boolean isSameCommand(String command) {
        return COMMAND_NAME.equals(command.split(" ")[0]);
    }
}
