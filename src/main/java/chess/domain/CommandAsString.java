package chess.domain;

import chess.domain.position.Coordinate;

public final class CommandAsString {

    private final String[] commandInputs;

    public CommandAsString() {
        this("");
    }

    public CommandAsString(final String stringCommand) {
        this(stringCommand.split(" "));
    }

    private CommandAsString(final String[] commandInputs) {
        this.commandInputs = commandInputs;
    }

    public Command command() {
        return Command.valueOf(commandInputs[0].toUpperCase());
    }

    public Coordinate source() {
        return positionOfCommand(1);
    }

    public Coordinate target() {
        return positionOfCommand(2);
    }

    private Coordinate positionOfCommand(final int number) {
        if (commandInputs.length == 1) {
            throw new IllegalArgumentException("플레이어의 행동이 아닙니다.");
        }
        return Coordinate.ofName(commandInputs[number]);
    }

    public boolean isStart() {
        return "start".equalsIgnoreCase(commandInputs[0]);
    }

    public boolean isEnd() {
        return "end".equalsIgnoreCase(commandInputs[0]);
    }

    public boolean isMove() {
        return "move".equalsIgnoreCase(commandInputs[0]);
    }

    public boolean isStatus() {
        return "status".equalsIgnoreCase(commandInputs[0]);
    }
}
