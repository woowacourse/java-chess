package chess.domain.command;

import java.util.Arrays;

public class Command {
    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private static final String STATUS = "status";
    public static final String BLANK = " ";
    public static final int FIRST_COMMAND_INDEX = 0;

    private final String firstCommand;
    private final String[] splitCommands;

    public Command(String fullCommands) {
        this(splitCommand(fullCommands));
    }

    public Command(String[] splitCommands) {
        this(splitCommands[FIRST_COMMAND_INDEX], splitCommands);
    }

    public Command(String firstCommand, String[] splitCommands) {
        this.firstCommand = firstCommand;
        this.splitCommands = splitCommands;
    }

    private static String[] splitCommand(String input) {
        String[] splitCommand = input.split(BLANK);
        validateCommand(splitCommand);
        return splitCommand;
    }

    private static void validateCommand(String[] values) {
        String firstCommand = values[FIRST_COMMAND_INDEX];
        if (!(START.equals(firstCommand) || END.equals(firstCommand) ||
                MOVE.equals(firstCommand) || STATUS.equals(firstCommand))) {
            throw new IllegalArgumentException("[ERROR] 올바른 명령어가 아닙니다.");
        }
    }

    public boolean isStart() {
        return START.equals(firstCommand);
    }

    public boolean isMove() {
        return MOVE.equals(firstCommand);
    }

    public boolean isEnd() {
        return END.equals(firstCommand);
    }

    public boolean isStatus() {
        return STATUS.equals(firstCommand);
    }

    public String[] secondAndThirdCommand() {
        return Arrays.copyOfRange(splitCommands, 1, 2);
    }

    public void validateRightFirstCommand() {
        if (!(isStart() || isEnd())) {
            throw new IllegalArgumentException("[ERROR] 올바른 첫 명령어가 아닙니다.");
        }
    }

    public void validateRunningCommand() {
        if (!(isMove() || isEnd() || isStatus())) {
            throw new IllegalArgumentException("[ERROR] 올바른 진행 명령어가 아닙니다.");
        }
    }
}
