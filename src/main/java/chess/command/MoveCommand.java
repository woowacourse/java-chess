package chess.command;

import chess.position.Position;

public class MoveCommand implements Command {

    public static final String DELIMITER = " ";
    public static final int MOVE_COMMAND_LENGTH = 3;
    public static final int POSITION_FROM = 1;
    public static final int POSITION_TO = 2;

    private final Position from;
    private final Position to;

    public MoveCommand(String command) {
        this(splitCommand(command));
    }

    private static String[] splitCommand(String command) {
        String[] commands = command.split(DELIMITER);
        validateCommandLength(commands);
        return commands;
    }

    private static void validateCommandLength(String[] commands) {
        if (commands.length != MOVE_COMMAND_LENGTH) {
            throw new IllegalArgumentException("[ERROR] move명령어가 잘못되었습니다.");
        }
    }

    public MoveCommand(String... position) {
        this(Position.from(position[POSITION_FROM]), Position.from(position[POSITION_TO]));
    }

    public MoveCommand(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isMove() {
        return true;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public Position from() {
        return from;
    }

    @Override
    public Position to() {
        return to;
    }
}
