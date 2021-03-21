package chess.domain.command;

import chess.controller.ChessController;

import java.util.Arrays;

public class Command {
    public static final String BLANK = " ";
    public static final int FIRST_COMMAND_INDEX = 0;

    private final FirstCommand firstCommand;
    private final String[] splitCommands;

    public Command(String fullCommands) {
        this(splitCommand(fullCommands));
    }

    public Command(String[] splitCommands) {
        this(splitCommands[FIRST_COMMAND_INDEX], splitCommands);
    }

    public Command(String firstCommand, String[] splitCommands) {
        this(FirstCommand.findFirstCommand(firstCommand), splitCommands);
    }

    public Command(FirstCommand firstCommand, String[] splitCommands) {
        this.firstCommand = firstCommand;
        this.splitCommands = splitCommands;
    }

    private static String[] splitCommand(String input) {
        return input.split(BLANK);
    }

    public String[] sourceAndTarget() {
        validateForMoveCommand(splitCommands);
        return Arrays.copyOfRange(splitCommands, 1, 3);
    }

    private void validateForMoveCommand(String[] splitCommands) {
        if (splitCommands.length != 3) {
            throw new IllegalArgumentException("[ERROR] move source위치 target위치 형식으로 입력해주세요");
        }
    }

    public void runFirstAction(ChessController chessController) {
        firstCommand.runFirstAction(chessController);
    }

    public void runRunningAction(ChessController chessController, Command command) {
        firstCommand.runRunningAction(chessController, command);
    }
}
