package chess.domain;

import chess.domain.position.Positions;

public class Command {
    private static final String START = "start";
    private static final String END = "end";
    private static final String STATUS = "status";
    private static final String MOVE = "move";

    private final String command;

    private Command(String command) {
        this.command = command;
    }

    public static Command from(String command) {
        validateCommand(command);
        return new Command(command);
    }

    private static void validateCommand(String command) {
        checkStartOrEnd(command);
    }

    private static void checkStartOrEnd(String command) {
        if (!command.equals(START) && !command.equals(END) && !command.equals(STATUS)) {
            checkMove(command);
        }
    }

    private static void checkMove(String command) {
        if (!command.startsWith(MOVE)) {
            throw new IllegalArgumentException("잘못된 커멘드 입니다.");
        }
    }

    public boolean isEnd() {
        return command.equals(END);
    }

    public boolean isMoveCommand() {
        return command.startsWith(MOVE);
    }

    public boolean isStart() {
        return command.equals(START);
    }

    public boolean isStatus() {
        return command.equals(STATUS);
    }

    public Positions makePositions() {
        checkMoveCommand();

        String[] token = command.split(" ");

        return Positions.from(token);
    }

    private void checkMoveCommand() {
        if (!isMoveCommand()) {
            throw new IllegalArgumentException("커맨드가 잘못되었습니다. move 커맨드가 와야 합니다.");
        }
    }
}
